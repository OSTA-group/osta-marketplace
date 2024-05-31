package com.osta.marketplace.repository;

import com.osta.marketplace.domain.Extension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing extensions.
 */
@Repository
public interface ExtensionRepository extends JpaRepository<Extension, UUID> {

    /**
     * Retrieves a list of extensions with their versions, filtered by area and name.
     *
     * @param area         The area of the extension (can be null).
     * @param nameContains A substring to filter extension names (can be null).
     * @param pageable     The pagination information.
     * @return A list of extensions.
     */
    @Query("""
            SELECT s FROM Extension s JOIN FETCH s.versions
            WHERE (:area IS NULL OR LOWER(s.area) = LOWER(:area))
            AND (:nameContains IS NULL OR upper(s.name) LIKE '%' || upper(:nameContains) || '%')
            """)
    List<Extension> findAllWithVersions(String area, String nameContains, Pageable pageable);
}
