package com.gini.services;

import com.gini.controller.request.PartRequest;
import com.gini.controller.response.PartResponse;
import com.gini.controller.response.base.ResponseStatus;
import com.gini.controller.response.base.ResponseStatusCode;
import com.gini.controller.response.base.RestResponse;
import com.gini.converter.PartConverter;
import com.gini.domain.entities.Part;
import com.gini.repositories.PartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;


    @Override
    @Transactional
    public PartResponse create(PartRequest request){

        Part part = PartConverter.convert(request);

        Part savedPart = partRepository.save(part);

        return PartConverter.convertToPartResponse(savedPart);
    }
}
