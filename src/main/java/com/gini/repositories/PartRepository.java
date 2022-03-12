package com.gini.repositories;

import com.gini.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PartRepository extends JpaRepository<Part, UUID> {

    @Override
    Optional<Part> findById(UUID uuid);
}