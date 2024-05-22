package com.osta.marketplace.controller.dto;

public record ExtensionVersionResponse(
        String url,
        String configurationUrl,
        String fileHash,
        int version
) {}
