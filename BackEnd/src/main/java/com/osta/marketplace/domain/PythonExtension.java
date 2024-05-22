package com.osta.marketplace.domain;

import jakarta.persistence.Entity;

import java.util.HashMap;
import java.util.Map;

@Entity
public class PythonExtension extends Extension {

    private String fileHash;

    @Override
    public Map<String, String> packProperties() {
        return new HashMap<>();
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }
}
