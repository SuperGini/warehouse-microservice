package com.gini.error.handler.errors;

public record ErrorResponse(
        String field,
        String message
) {
}
