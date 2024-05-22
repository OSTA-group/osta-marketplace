package com.osta.marketplace.service;

import com.osta.marketplace.domain.Extension;
import com.osta.marketplace.domain.ExtensionVersion;
import com.osta.marketplace.exception.NoVersionException;
import com.osta.marketplace.exception.ResourceNotFoundException;
import com.osta.marketplace.repository.ExtensionRepository;
import com.osta.marketplace.service.dto.NewestExtensionDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.UUID;

/**
 * Service class for managing extensions.
 */
@Service
public class ExtensionService {

    private final static int PAGE_ITEM_COUNT = 10;

    private final ExtensionRepository extensionRepository;

    /**
     * Constructs an `ExtensionService` with the provided `ExtensionRepository`.
     *
     * @param extensionRepository The repository for extensions.
     */
    public ExtensionService(ExtensionRepository extensionRepository) {
        this.extensionRepository = extensionRepository;
    }

    /**
     * Retrieves details for a specific extension by its ID.
     *
     * @param extensionId The ID of the extension.
     * @return The extension details.
     * @throws ResourceNotFoundException If the extension is not found.
     */
    public Extension getExtensionDetails(UUID extensionId) {
        return this.extensionRepository.findById(extensionId).orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Retrieves a collection of extensions with their most recent versions.
     *
     * @param area         The area of the extension.
     * @param nameContains A substring to filter extension names.
     * @param page         The page number for pagination.
     * @return A collection of `NewestExtensionDto` objects.
     */
    public Collection<NewestExtensionDto> getExtensionsWithMostRecentVersion(String area, String nameContains, int page) {
        Collection<Extension> extensions = this.extensionRepository.findAllWithVersions(area, nameContains, PageRequest.of(page, PAGE_ITEM_COUNT));

        return extensions.stream()
                .map(extension ->
                        new NewestExtensionDto(
                                extension,
                                extension.getVersions()
                                        .stream().max(Comparator.comparing(ExtensionVersion::getVersion))
                                        .orElseThrow(NoVersionException::new)
                        )
                ).toList();
    }
}
