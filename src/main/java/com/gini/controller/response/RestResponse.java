package com.gini.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {

    private ResponseStatus responseStatus;
    private T response;

}
