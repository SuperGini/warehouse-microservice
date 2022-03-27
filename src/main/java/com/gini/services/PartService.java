package com.gini.services;

import com.gini.controller.request.PartRequest;
import com.gini.controller.response.CreatePartResponse;
import com.gini.controller.response.ListPartsResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PartService {
    @Transactional
    CreatePartResponse create(PartRequest request);

    @Transactional
    List<ListPartsResponse> findAllPartsWithPagination(String pageNumber);
}
