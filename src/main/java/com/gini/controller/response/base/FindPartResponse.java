package com.gini.controller.response.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gini.domain.enums.Manufacturer;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
public class FindPartResponse {

    private UUID id;
    private String partName;
    private Integer partCount;
    private String partNumber;
    private BigDecimal price;
    private String currency;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Manufacturer manufacturer;
}
