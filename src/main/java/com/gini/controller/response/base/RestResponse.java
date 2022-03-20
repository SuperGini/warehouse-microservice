package com.gini.controller.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {

    private ResponseStatus responseStatus;
    private T response;

}
