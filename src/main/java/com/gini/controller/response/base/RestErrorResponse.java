package com.gini.controller.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestErrorResponse<T> implements Error {

    private ErrorCode errorCode;
    private String errorMessage;
    private T errors;

}
