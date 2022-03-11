package com.gini.domain.entities;

import com.gini.domain.enums.Constructor;

import java.time.LocalDate;
import java.util.UUID;

public class CarModel {

    private UUID id;
    private Constructor constructor;
    private String model;

    private LocalDate year;
    private String engineType;
}
