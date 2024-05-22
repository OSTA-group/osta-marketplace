package com.osta.marketplace.controller.dto;

import java.util.Map;
import java.util.UUID;

public record ExtensionResponse(
        UUID id,
        String name,
        String type,
        String area,
        String languages,
        String url,
        String configurationUrl,
        String fileHash,
        int version,
        Map<String, String> properties
) {}
