package com.osta.marketplace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osta.marketplace.controller.dto.ExtensionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        ).andExpect(result -> {
            var extensions = objectMapper.readValue(result.getResponse().getContentAsString(), ExtensionResponse.class);
        });
    }
}
