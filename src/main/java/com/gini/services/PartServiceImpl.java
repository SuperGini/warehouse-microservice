package com.gini.services;

import com.gini.controller.request.PartRequest;
import com.gini.controller.response.PartResponse;
import com.gini.converter.PartConverter;
import com.gini.domain.entities.Part;
import com.gini.error.handler.exceptions.PartAlreadyExists;
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
    public PartResponse create(PartRequest request) {

        checkIfPartAllreadyExists(request);

        Part part = PartConverter.convert(request);

        Part savedPart = partRepository.save(part);

        return PartConverter.convertToPartResponse(savedPart);
    }

    private void checkIfPartAllreadyExists(PartRequest request) {
        partRepository.findByPartNumber(request.getPartNumber())
                      .ifPresent(s -> {throw new PartAlreadyExists("Part already exists in database. Another one can't be created");});
    }
}
