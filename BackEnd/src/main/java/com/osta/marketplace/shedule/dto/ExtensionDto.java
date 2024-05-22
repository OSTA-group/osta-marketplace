package com.osta.marketplace.shedule.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;

public record ExtensionDto(
        @JsonIgnore int id,
        String name,
        String description,
        String type,
        String area,
        String languages,
        Map<String, String> properties,
        List<ExtensionVersionDto> versions
) {}
