package com.gini.controller.response.base;

import lombok.Getter;

@Getter
public enum ResponseStatusCode {

    CREATED("created"),
    UPDATED("updated"),
    VALIDATION_ERROR("Validation error");



    private final String message;

    ResponseStatusCode(String message) {
        this.message = message;
    }
}
