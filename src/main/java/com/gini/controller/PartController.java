package com.gini.controller;

import com.gini.controller.request.PartRequest;
import com.gini.domain.entities.Part;
import com.gini.services.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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



    @PostMapping("/parts")          //for the @Valid to work we need Hibernate Validator on classpath
    ResponseEntity<Object> createPart(@Valid @RequestBody PartRequest request) {

        Part part = partService.create(request);

        System.out.println(part.getId().toString());

        return new ResponseEntity<>(part.getId().toString(), HttpStatus.CREATED);
    }
}
