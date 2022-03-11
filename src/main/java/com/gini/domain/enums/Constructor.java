package com.gini.domain.enums;

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


    public final String constructor;

    Constructor(String constructor) {
        this.constructor = constructor;
    }
}
