package com.osta.marketplace.service.dto;

import com.osta.marketplace.domain.Extension;
import com.osta.marketplace.domain.ExtensionVersion;

public record NewestExtensionDto(
        Extension extension,
        ExtensionVersion version
) {
}
