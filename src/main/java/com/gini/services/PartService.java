package com.gini.services;

import com.gini.controller.request.PartRequest;
import com.gini.domain.entities.Part;
import org.springframework.transaction.annotation.Transactional;

public interface PartService {

    @Transactional
    Part create(PartRequest request);
}
