package com.gini.controller.response.base;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ResponseStatus(

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    ErrorCode statusCode,
    String message

) {
}
