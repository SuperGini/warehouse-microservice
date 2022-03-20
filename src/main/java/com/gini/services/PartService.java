package com.gini.services;

import com.gini.controller.request.PartRequest;
import com.gini.controller.response.PartResponse;
import com.gini.controller.response.base.RestResponse;
import com.gini.domain.entities.Part;
import org.springframework.transaction.annotation.Transactional;

public interface PartService {
    @Transactional
    PartResponse create(PartRequest request);
}
