package com.gini.error.handler;

import com.gini.controller.response.ResponseStatus;
import com.gini.controller.response.ResponseStatusCode;
import com.gini.controller.response.RestResponse;
import com.gini.error.handler.errors.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        RestResponse<List<ErrorResponse>> restResponse = createErrorResponse(ex);

        return ResponseEntity.badRequest().body(restResponse);
    }

    private RestResponse<List<ErrorResponse>> createErrorResponse(MethodArgumentNotValidException ex) {
        List<ErrorResponse> errors = new ArrayList<>();

        ex.getBindingResult()
          .getFieldErrors()
          .forEach( error -> addErrorToErrorsList(errors, error));

        ResponseStatus responseStatus = new ResponseStatus(ResponseStatusCode.VALIDATION_ERROR,
                                                            ResponseStatusCode.VALIDATION_ERROR.getMessage());

        RestResponse<List<ErrorResponse>> restResponse = new RestResponse<>();
        restResponse.setResponseStatus(responseStatus);
        restResponse.setResponse(errors);
        return restResponse;
    }

    private void addErrorToErrorsList(List<ErrorResponse> errors, FieldError x) {
        String field = x.getField();
        String message = x.getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(field, message);
        errors.add(errorResponse);
    }
}
