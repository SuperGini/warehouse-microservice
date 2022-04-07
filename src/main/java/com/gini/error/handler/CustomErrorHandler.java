package com.gini.error.handler;

import com.gini.controller.response.base.Error;
import com.gini.controller.response.base.ErrorCode;
import com.gini.controller.response.base.RestErrorResponse;
import com.gini.error.handler.errors.ErrorResponse;
import com.gini.error.handler.exceptions.PartAlreadyExists;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        RestErrorResponse<List<ErrorResponse>> restResponse = createErrorResponse(ex);

        return ResponseEntity.badRequest().body(restResponse);
    }

    private RestErrorResponse<List<ErrorResponse>> createErrorResponse(MethodArgumentNotValidException ex) {

        List<ErrorResponse> errors = new ArrayList<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> addErrorToErrorsList(errors, error));

        RestErrorResponse<List<ErrorResponse>> restResponse = new RestErrorResponse<>();
        restResponse.setErrorCode(ErrorCode.VALIDATION_ERROR);
        restResponse.setErrorMessage(ErrorCode.VALIDATION_ERROR.getMessage());
        restResponse.setErrors(errors);
        return restResponse;
    }


    @ExceptionHandler(PartAlreadyExists.class)
    public ResponseEntity<Object> handlePartAlreadyExistsException(PartAlreadyExists ex) {
        log.error("Duplicate part found in database: ", ex);

        RestErrorResponse<String> restResponse = createErrorResponseBody(ErrorCode.DUPLICATE_PART_FOUND);

        return ResponseEntity.badRequest().body(restResponse);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Error> handleNumberFormatException(NumberFormatException ex) {
        log.error("The format {} is wrong. Accepting only numbers: ", ex.getMessage().toLowerCase(), ex);

        RestErrorResponse<String> restErrorResponse = createErrorResponseBody(ErrorCode.INVALID_FORMAT);

        return ResponseEntity.badRequest().body(restErrorResponse);
    }

    @ExceptionHandler(MysqlDataTruncation.class)
    public ResponseEntity<Error> handleMysqlDataTruncation(MysqlDataTruncation ex){
        log.error("The number of parts can not be below zero. ", ex);

        RestErrorResponse<String> restErrorResponse = createErrorResponseBody(ErrorCode.NEGATIVE_PART_COUNT);

        return ResponseEntity.badRequest().body(restErrorResponse);
    }


    private RestErrorResponse<String> createErrorResponseBody(ErrorCode errorCode) {
        RestErrorResponse<String> restResponse = new RestErrorResponse<>();
        restResponse.setErrorCode(errorCode);
        restResponse.setErrorMessage(errorCode.getMessage());
        return restResponse;
    }

    private void addErrorToErrorsList(List<ErrorResponse> errors, FieldError x) {
        String field = x.getField();
        String message = x.getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(field, message);
        errors.add(errorResponse);
    }


}
