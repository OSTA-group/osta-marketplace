package com.osta.marketplace.shedule.dto;

public record ExtensionVersionDto(
        int version,
        String url,
        String configurationUrl,
        String fileHash
) {}
