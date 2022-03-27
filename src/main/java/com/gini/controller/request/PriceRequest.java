package com.gini.controller.request;

import com.gini.validations.annotation.ValidCurrency;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class PriceRequest {

    @NotNull
    private String price;

    @NotNull
    @ValidCurrency
    private String currency;
}
