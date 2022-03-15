package com.gini.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Currency;

@Data
public class PriceRequest {

    @NotNull
    private String price;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Currency currency;
}
