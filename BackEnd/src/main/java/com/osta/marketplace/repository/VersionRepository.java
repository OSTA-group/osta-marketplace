package com.osta.marketplace.repository;

import com.osta.marketplace.domain.ExtensionVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<ExtensionVersion, Integer> {
}
