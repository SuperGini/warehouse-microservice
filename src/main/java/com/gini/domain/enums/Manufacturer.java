package com.gini.domain.enums;

public enum Manufacturer {

    LUK("Luk"),
    DELPHI("Delphi"),
    BOSCH("Bosch"),
    SAKS("Saks");

    public final String manufacturer;

    Manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
