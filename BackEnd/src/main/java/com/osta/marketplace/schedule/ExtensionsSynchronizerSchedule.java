package com.osta.marketplace.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osta.marketplace.domain.Extension;
import com.osta.marketplace.domain.ExtensionVersion;
import com.osta.marketplace.domain.PythonExtension;
import com.osta.marketplace.domain.WebExtension;
import com.osta.marketplace.repository.ExtensionRepository;
import com.osta.marketplace.repository.VersionRepository;
import com.osta.marketplace.schedule.dto.ExtensionDto;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.MissingResourceException;

@Component
@Profile("!test")
@EnableScheduling
public class ExtensionsSynchronizerSchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtensionsSynchronizerSchedule.class);

    private final ExtensionRepository extensionRepository;
    private final VersionRepository versionRepository;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${osta.data-url}")
    private String databaseUrl = "";
    private String previousHash = "";

    public ExtensionsSynchronizerSchedule(ExtensionRepository extensionRepository, VersionRepository versionRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.extensionRepository = extensionRepository;
        this.versionRepository = versionRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void onStartup() {
        updateDatabaseFromJson();
    }

    @Scheduled(fixedRate = 3600000) // Runs every hour
    public void updateDatabaseFromJson() {
        try {
            String jsonContent = this.restTemplate.getForObject(databaseUrl, String.class);

            if (jsonContent == null) {
                throw new MissingResourceException("Could not download extensions.json", "extensions", databaseUrl);
            }

            String currentHash = computeMD5Hash(jsonContent);

            if (!currentHash.equals(previousHash)) {
                ExtensionDto[] extensions = this.objectMapper.readValue(jsonContent, ExtensionDto[].class);
                updateDatabase(extensions);

                this.previousHash = currentHash;
            }
        } catch (MissingResourceException | RestClientException | NoSuchAlgorithmException | JsonProcessingException e) {
            LOGGER.error("Failed to download extensions from GitHub: {}", e.getLocalizedMessage(), e);
        }
    }

    private String computeMD5Hash(String content) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(content.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private void updateDatabase(ExtensionDto[] extensions) {
        if(extensions.length > 0) {
            this.extensionRepository.deleteAll();
        }

        for (var dto : extensions) {
            Extension extension = createExtensionEntity(dto);
            this.extensionRepository.save(extension);
            this.versionRepository.saveAll(extension.getVersions());
        }
    }

    private Extension createExtensionEntity(ExtensionDto dto) {
        Extension extension;

        switch (dto.type().toLowerCase()) {
            case "python":
                extension = new PythonExtension();
                ((PythonExtension) extension).setFileHash(dto.versions().getFirst().fileHash());
                break;
            case "web":
                extension = new WebExtension();
                ((WebExtension) extension).setUrlFilterable(Boolean.parseBoolean(dto.properties().get("urlFilterable")));
                break;
            default:
                throw new IllegalArgumentException("Unknown extension type: " + dto.type());
        }

        extension.setName(dto.name());
        extension.setDescription(dto.description());
        extension.setType(dto.type());
        extension.setArea(dto.area());
        extension.setLanguages(dto.languages());

        List<ExtensionVersion> versions = dto.versions().stream()
                .map(versionDto -> {
                    ExtensionVersion version = new ExtensionVersion();
                    version.setVersion(versionDto.version());
                    version.setUrl(versionDto.url());
                    version.setConfigurationUrl(versionDto.configurationUrl());
                    version.setFileHash(versionDto.fileHash());
                    version.setExtension(extension);
                    return version;
                }).toList();

        extension.setVersions(versions);
        return extension;
    }
}
