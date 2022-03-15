package com.gini.domain.enums;

import lombok.Getter;

@Getter
public enum Manufacturer {

    LUK("Luk"),
    DELPHI("Delphi"),
    BOSCH("Bosch"),
    SAKS("Saks");

    private final String manufacturer;

    Manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
