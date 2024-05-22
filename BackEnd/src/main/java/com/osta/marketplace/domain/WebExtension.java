package com.osta.marketplace.domain;

import jakarta.persistence.Entity;

import java.util.HashMap;
import java.util.Map;

@Entity
public class WebExtension extends Extension {

    private boolean urlFilterable;

    @Override
    public Map<String, String> packProperties() {
        Map<String, String> props = new HashMap<>();
        props.put("urlFilterable", String.valueOf(urlFilterable));
        return props;
    }

    public boolean isUrlFilterable() {
        return urlFilterable;
    }

    public void setUrlFilterable(boolean urlFilterable) {
        this.urlFilterable = urlFilterable;
    }
}
