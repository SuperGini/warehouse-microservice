package com.gini.controller;

import com.gini.controller.request.PartRequest;
import com.gini.controller.response.CreatePartResponse;
import com.gini.controller.response.ListPartsResponse;
import com.gini.services.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public record PartController(
        PartService partService
) {


    @PostMapping("/parts")
    public ResponseEntity<Object> createPart(@Valid @RequestBody PartRequest request) {

        CreatePartResponse partResponse = partService.create(request);

        return new ResponseEntity<>(partResponse, HttpStatus.CREATED);
    }


    @GetMapping("/parts/{pageNumber}")
    public ResponseEntity<List<ListPartsResponse>> findAllPartsWithPagination(@PathVariable String pageNumber) {

        List<ListPartsResponse> parts = partService.findAllPartsWithPagination(pageNumber);

        return new ResponseEntity<>(parts, HttpStatus.OK);
    }
}
