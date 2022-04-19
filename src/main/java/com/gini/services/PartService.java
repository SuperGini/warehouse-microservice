package com.gini.services;

import com.gini.controller.request.CreatePartRequest;
import com.gini.controller.request.UpdatePartRequest;
import com.gini.controller.response.CreatePartResponse;
import com.gini.controller.response.ListPartsResponse;
import com.gini.controller.response.base.FindPartResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PartService {
    @Transactional
    CreatePartResponse create(CreatePartRequest request);

    @Transactional
    List<ListPartsResponse> findAllPartsWithPagination(String pageNumber);

    @Transactional
    Integer findPartsCount();

    @Transactional
    int addParts(UpdatePartRequest updatePart);

    @Transactional
    FindPartResponse findPartByPartNumber(String partNumber);

    @Transactional
    FindPartResponse updatePartPrice(String partNumber, String partPrice);
}
