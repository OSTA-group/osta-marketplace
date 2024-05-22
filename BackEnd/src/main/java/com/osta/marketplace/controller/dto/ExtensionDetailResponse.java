package com.osta.marketplace.controller.dto;

import java.util.Collection;
import java.util.Map;

public record ExtensionDetailResponse(
        String name,
        String description,
        String type,
        String area,
        String languages,
        Map<String, String> properties,
        Collection<ExtensionVersionResponse> versions
) {}
