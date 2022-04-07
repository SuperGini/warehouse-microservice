package com.gini.services;

import com.gini.controller.request.CreatePartRequest;
import com.gini.controller.request.UpdatePartRequest;
import com.gini.controller.response.CreatePartResponse;
import com.gini.controller.response.ListPartsResponse;
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
    void updatePart(String partId, String suplayerId);
}
