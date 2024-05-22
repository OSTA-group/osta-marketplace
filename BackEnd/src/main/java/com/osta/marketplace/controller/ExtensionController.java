package com.osta.marketplace.controller;

import com.osta.marketplace.controller.dto.ExtensionDetailResponse;
import com.osta.marketplace.controller.dto.ExtensionResponse;
import com.osta.marketplace.controller.dto.ExtensionVersionResponse;
import com.osta.marketplace.domain.Extension;
import com.osta.marketplace.service.ExtensionService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/extensions")
public class ExtensionController {

    private final ExtensionService extensionService;

    public ExtensionController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<Collection<ExtensionResponse>> getExtensions(@RequestParam(required = false) String area, @RequestParam(required = false) String nameContains, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(
                this.extensionService.getExtensionsWithMostRecentVersion(area, nameContains, page)
                        .stream()
                        .map(extension ->
                                new ExtensionResponse(
                                        extension.extension().getId(),
                                        extension.extension().getName(),
                                        extension.extension().getType(),
                                        extension.extension().getArea(),
                                        extension.extension().getLanguages(),
                                        extension.version().getUrl(),
                                        extension.version().getConfigurationUrl(),
                                        extension.version().getFileHash(),
                                        extension.version().getVersion(),
                                        extension.extension().packProperties()
                                )
                        ).toList());
    }

    @GetMapping("/{extensionId}")
    @Transactional(readOnly = true)
    public ResponseEntity<ExtensionDetailResponse> getExtensionDetails(@PathVariable UUID extensionId) {
        Extension requestedExtension = this.extensionService.getExtensionDetails(extensionId);

        return ResponseEntity.ok(new ExtensionDetailResponse(
                requestedExtension.getName(),
                requestedExtension.getDescription(),
                requestedExtension.getType(),
                requestedExtension.getArea(),
                requestedExtension.getLanguages(),
                requestedExtension.packProperties(),
                requestedExtension.getVersions().stream().map(extension -> new ExtensionVersionResponse(extension.getUrl(), extension.getConfigurationUrl(), extension.getFileHash(), extension.getVersion())).toList()
        ));
    }
}
