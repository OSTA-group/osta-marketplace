package com.osta.marketplace.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osta.marketplace.controller.dto.ExtensionDetailResponse;
import com.osta.marketplace.controller.dto.ExtensionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExtensionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetExtensions_noParams() throws Exception {
        this.mockMvc.perform(
                get("/api/extensions").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(result -> {
            var extensions = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ExtensionResponse>>() {
            });

            assertAll(() -> {
                assertFalse(extensions.isEmpty());
                assertEquals(3, extensions.size());
                assertEquals("test-web", extensions.getFirst().name());
                assertEquals("web", extensions.getFirst().type());
                assertEquals("python", extensions.get(2).type());
            });
        }).andDo(print());
    }

    @Test
    public void testGetExtensions_areaParam() throws Exception {
        this.mockMvc.perform(
                get("/api/extensions?area=global").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(result -> {
            var extensions = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ExtensionResponse>>() {
            });

            assertAll(() -> {
                assertFalse(extensions.isEmpty());
                assertEquals(2, extensions.size());
                assertEquals(2, extensions.stream().filter(extension -> extension.area().equalsIgnoreCase("global")).count());
            });
        }).andDo(print());
    }

    @Test
    public void testGetExtensions_nameParam() throws Exception {
        this.mockMvc.perform(
                get("/api/extensions?nameContains=web").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(result -> {
            var extensions = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ExtensionResponse>>() {
            });

            assertAll(() -> {
                assertFalse(extensions.isEmpty());
                assertEquals(2, extensions.size());
                assertEquals(2, extensions.stream().filter(extension -> extension.name().contains("web")).count());
            });
        }).andDo(print());
    }

    @Test
    public void testGetExtensions_areaAndNameParam() throws Exception {
        this.mockMvc.perform(
                get("/api/extensions?nameContains=web&area=global").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(result -> {
            var extensions = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ExtensionResponse>>() {
            });

            assertAll(() -> {
                assertFalse(extensions.isEmpty());
                assertEquals(1, extensions.size());
                assertEquals(1, extensions.stream().filter(extension -> extension.name().contains("web")).count());
                assertEquals(1, extensions.stream().filter(extension -> extension.area().equalsIgnoreCase("global")).count());
            });
        }).andDo(print());
    }

    @Test
    public void testGetExtensions_pageParam() throws Exception {
        this.mockMvc.perform(
                get("/api/extensions?page=1").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(result -> {
            var extensions = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ExtensionResponse>>() {
            });

            assertAll(() -> assertTrue(extensions.isEmpty()));
        }).andDo(print());
    }

    @Test
    public void testGetExtensionDetails_noID() throws Exception {
        this.mockMvc.perform(
                        get("/api/extensions/").contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("No static resource api/extensions.", result.getResponse().getErrorMessage()))
                .andDo(print());
    }

    @Test
    public void testGetExtensionDetails_invalidID() throws Exception {
        this.mockMvc.perform(
                        get("/api/extensions/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("Could not find the requested extension.", result.getResponse().getErrorMessage()))
                .andDo(print());
    }

    @Test
    public void testGetExtensionDetails_validID() throws Exception {
        this.mockMvc.perform(
                get("/api/extensions/00000000-0000-0000-0000-000000000001").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(result -> {
            var extension = this.objectMapper.readValue(result.getResponse().getContentAsString(), ExtensionDetailResponse.class);

            assertAll(() -> {
                assertEquals("test-web", extension.name());
                assertEquals(1, extension.versions().size());
            });
        }).andDo(print());
    }

    @Test
    public void testGetExtensionDetails_noVersions() throws Exception {
        this.mockMvc.perform(
                        get("/api/extensions/00000000-0000-0000-0000-000000000004").contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotAcceptable())
                .andExpect(result -> assertEquals("Could not find a version for this extension.", result.getResponse().getErrorMessage()))
                .andDo(print());
    }
}
