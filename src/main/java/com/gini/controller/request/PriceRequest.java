package com.gini.controller.request;

import com.gini.validations.annotation.ValidCurrency;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.Valid;

@Data

public class PriceRequest {

    @NotNull
    private String price;

    @NotNull
    @ValidCurrency
    private String currency;
}
