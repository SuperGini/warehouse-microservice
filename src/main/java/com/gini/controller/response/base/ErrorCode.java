package com.gini.controller.response.base;

import lombok.Getter;

@Getter
public enum ErrorCode {

    VALIDATION_ERROR("validation error"),
    DUPLICATE_PART_FOUND("part already exists");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}