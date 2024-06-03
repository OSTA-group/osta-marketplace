package com.osta.marketplace.schedule.dto;

public record ExtensionVersionDto(
        int version,
        String url,
        String configurationUrl,
        String fileHash
) {}
