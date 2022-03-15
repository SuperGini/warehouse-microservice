package com.gini.controller.request;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class PartRequest {

    @NotNull
    private String partName;

    @NotNull
    private String partNumber;

    @NotNull
    private String partCount;

    @NotNull
    private PriceRequest partPrice;

    private CarModelRequest carModel;

    @NotNull
    private String suplayerName;
    private String manufacturer;

}
