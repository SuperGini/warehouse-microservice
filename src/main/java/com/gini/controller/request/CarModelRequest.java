package com.gini.controller.request;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CarModelRequest {

    @NotNull
    private String constructor;

    @NotNull
    private String model;

    private String year;

    private String engine;

}
