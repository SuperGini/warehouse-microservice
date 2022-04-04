package com.gini.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CreatePartResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private UUID id;
    private String partName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer partCount;
    private String partNumber;
    private PriceResponse price;
    private String partSpecification;
    private String manufacturer;
}
