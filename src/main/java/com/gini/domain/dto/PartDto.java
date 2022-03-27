package com.gini.domain.dto;

import com.gini.domain.enums.Manufacturer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;


public record PartDto(
        UUID id,
        String partName,
        BigInteger partCount,
        String partNumber,
        BigDecimal price,
        String currency,
        BigDecimal discount,
        BigDecimal vat,
        String partSpecification,
        Manufacturer manufacturer
) {
}
