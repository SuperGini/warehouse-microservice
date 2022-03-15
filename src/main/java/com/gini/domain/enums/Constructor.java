package com.gini.domain.enums;

import lombok.Getter;

@Getter
public enum Constructor {

    DACIA("Dacia"),
    FERRARI("Ferrari"),
    MERCEDES_BENZ("Mercedes-Benz"),
    AUDI("Audi"),
    FORD("Ford"),
    SKODA("Skoda"),
    PORSCHE("Porsche"),
    PEUGEOT("Peugeot"),
    RENAULT("renault");


    private final String constructor;

    Constructor(String constructor) {
        this.constructor = constructor;
    }
}
