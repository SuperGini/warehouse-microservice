package com.gini.controller;

import com.gini.controller.request.PartRequest;
import com.gini.controller.response.PartResponse;
import com.gini.services.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public record PartController(
        PartService partService
) {


    @PostMapping("/parts")
        //for the @Valid to work we need Hibernate Validator on classpath
    ResponseEntity<Object> createPart(@Valid @RequestBody PartRequest request) {

        PartResponse partResponse = partService.create(request);

        return new ResponseEntity<>(partResponse, HttpStatus.CREATED);
    }
}
