package com.gini.controller.response;

import lombok.Getter;

@Getter
public enum ResponseStatusCode {

    OK("ok"),
    ERROR("error"),
    VALIDATION_ERROR("Validation error");


    private final String message;

    ResponseStatusCode(String message) {
        this.message = message;
    }
}
