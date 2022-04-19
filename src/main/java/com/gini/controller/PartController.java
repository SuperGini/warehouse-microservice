package com.gini.controller;

import com.gini.controller.request.CreatePartRequest;
import com.gini.controller.request.UpdatePartRequest;
import com.gini.controller.response.CreatePartResponse;
import com.gini.controller.response.ListPartsResponse;
import com.gini.controller.response.UpdatePartResponse;
import com.gini.controller.response.base.FindPartResponse;
import com.gini.services.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Object> createPart(@Valid @RequestBody CreatePartRequest request) {

        CreatePartResponse partResponse = partService.create(request);

        return new ResponseEntity<>(partResponse, HttpStatus.CREATED);
    }


    @GetMapping("/parts/{pageNumber}")
    public ResponseEntity<List<ListPartsResponse>> findAllPartsWithPagination(@PathVariable String pageNumber) {

        List<ListPartsResponse> parts = partService.findAllPartsWithPagination(pageNumber);

        return new ResponseEntity<>(parts, HttpStatus.OK);
    }


    @GetMapping("/parts/count")
    public ResponseEntity<Integer> findPartsCount() {

        Integer count = partService().findPartsCount();

        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PutMapping("/parts")
    public ResponseEntity<Integer> updatePart(@RequestBody UpdatePartRequest partRequest) {

        Integer ok = partService.addParts(partRequest);

        return ResponseEntity.ok().body(ok);
    }

    @GetMapping("/parts/part/{partNumber}")
    public ResponseEntity<FindPartResponse> findPartByPartNumber(@PathVariable String partNumber){
        return ResponseEntity
                            .ok()
                                .body(partService.findPartByPartNumber(partNumber));
    }

    @PutMapping("/parts/part/{partNumber}/{partPrice}")
    public ResponseEntity<FindPartResponse> updatePartPrice(@PathVariable String partNumber, @PathVariable String partPrice){


        return new ResponseEntity<>(partService.updatePartPrice(partNumber, partPrice), HttpStatus.OK);

    }

}
