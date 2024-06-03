package com.osta.marketplace.schedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osta.marketplace.repository.ExtensionRepository;
import com.osta.marketplace.repository.VersionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@DirtiesContext
class ExtensionsSynchronizerScheduleTest {

    @Autowired
    private ExtensionRepository extensionRepository;

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private RestTemplate restTemplate;

    private ExtensionsSynchronizerSchedule extensionsSynchronizerSchedule;

    @BeforeEach
    public void setUp() {
        extensionsSynchronizerSchedule = new ExtensionsSynchronizerSchedule(extensionRepository, versionRepository, restTemplate, objectMapper);
    }

    @Test
    void updateDatabaseFromJson() {
        String mockedJsonResult = """
                [
                  {
                    "name": "Test json update adaper",
                    "description": "Very informative description",
                    "type": "web",
                    "area": "None",
                    "languages": "None",
                    "properties": {
                    },
                    "versions": [
                      {
                        "version": 1,
                        "url": null,
                        "configurationUrl": null,
                        "fileHash": null
                      }
                    ]
                  }
                ]
                """;

        given(restTemplate.getForObject(anyString(), eq(String.class))).willReturn(mockedJsonResult);

        this.extensionsSynchronizerSchedule.updateDatabaseFromJson();

        var extensions = this.extensionRepository.findAll();

        assertEquals(1, extensions.size());
        assertEquals("Test json update adaper", extensions.getFirst().getName());
    }
}