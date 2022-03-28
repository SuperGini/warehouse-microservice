package com.gini.repositories;

import com.gini.domain.dto.PartDto;
import com.gini.domain.dto.PartNumberDto;
import com.gini.domain.entities.Part;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional(propagation = Propagation.MANDATORY)
public interface PartRepository extends JpaRepository<Part, UUID> {

    @Override
    Optional<Part> findById(UUID uuid);

    Optional<PartNumberDto> findByPartNumber(String partNumber);

    //https://vladmihalcea.com/the-best-way-to-map-a-projection-query-to-a-dto-with-jpa-and-hibernate/
    //https://www.baeldung.com/spring-data-jpa-pagination-sorting
    //https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-seven-pagination/
    @Query("SELECT" +
            " new com.gini.domain.dto.PartDto(" +
            " part.id, part.partName, part.partCount, part.partNumber, price.price, price.currency," +
            " price.discount, price.vat, specs.specifications, part.manufacturer)" +
            " FROM Part part" +
            " JOIN part.price AS price" +
            " LEFT JOIN part.partSpecifications AS specs" +
            " ORDER BY part.updated ASC")
    List<PartDto> findAllPartsWithPagination(Pageable pageable);

    @Query("SELECT COUNT (p.id) FROM Part p")
    int contPartNumber();
}