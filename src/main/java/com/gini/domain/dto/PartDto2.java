package com.gini.domain.dto;

import com.gini.domain.enums.Manufacturer;

import java.math.BigDecimal;
import java.util.UUID;


public record PartDto2 (
    UUID id,
    String partName,
    Integer partCount,
    String partNumber,
    BigDecimal price,
    String currency,
    Manufacturer manufacturer
){
}
