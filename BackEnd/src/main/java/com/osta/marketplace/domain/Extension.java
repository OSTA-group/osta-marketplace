package com.osta.marketplace.domain;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "extensions")
@Inheritance
public abstract class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(length = 1024)
    private String description;
    private String type;
    private String area;
    private String languages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "extension", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Collection<ExtensionVersion> versions;

    public abstract Map<String, String> packProperties();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Extension extension)) return false;
        return Objects.equals(getName(), extension.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Collection<ExtensionVersion> getVersions() {
        return versions;
    }

    public void setVersions(Collection<ExtensionVersion> versions) {
        this.versions = versions;
    }
}
