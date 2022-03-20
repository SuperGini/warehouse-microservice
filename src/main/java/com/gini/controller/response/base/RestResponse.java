package com.gini.controller.response.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {

    private ResponseStatus responseStatus;
    private T response;

}
