package com.gini.repositories;

import com.gini.domain.dto.PartDto;
import com.gini.domain.dto.PartDto2;
import com.gini.domain.dto.PartNumberDto;
import com.gini.domain.entities.Part;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Query("""
        
        SELECT
        new com.gini.domain.dto.PartDto( 
                 part.id, part.partName, part.partCount, part.partNumber, price.price, price.currency,
                 price.discount, price.vat, specs.specifications, part.manufacturer)
        FROM Part part
                 JOIN part.price AS price
                 LEFT JOIN part.partSpecifications AS specs
        ORDER BY part.updated ASC
        """)
    List<PartDto> findAllPartsWithPagination(Pageable pageable);

    @Query("SELECT COUNT (p.id) FROM Part p")
    int contPartNumber();


    @Query(""" 
            SELECT part FROM Part part
                JOIN part.price AS price
                JOIN part.suplayers AS suplayer
                JOIN suplayer.count AS countx ON countx.part = part.suplayerPartCount
            WHERE part.id = :partId AND suplayer.id = :suplayerId
            """)
    Optional<Part> findPartToUpdate(@Param("partId") UUID partId, @Param("suplayerId") UUID suplayerId);

    @Query("""
            SELECT new com.gini.domain.dto.PartDto2(
                p.id,
                p.partName,
                p.partCount,
                p.partNumber,
                price.price,
                price.currency,
                p.manufacturer)
            FROM Part p 
            JOIN p.price AS price
            WHERE p.partNumber = :partNumber
            """)
    Optional<PartDto2> findPartByPartNumberVersion2(@Param("partNumber") String partNumber);

    //mai intai facem flush la toate entitatile in baza de date dupa care facem clear la persistance context
    //daca am face mai intai clear la persistance contex e posibil sa avem enitati modificate in persitance context
    //care nu apuca sa aduca modificarile in baza de date
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = """
        UPDATE part p JOIN price pr ON p.price_id = pr.id
                                     SET pr.price =:price WHERE p.part_number =:partNumber
        """,
            nativeQuery = true
    )
    int updatePrice(@Param("partNumber") String partNumber, @Param("price") BigDecimal price);


    Optional<Part> findPartByPartNumber(String partNumber);









}