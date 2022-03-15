package com.gini.services;

import com.gini.controller.request.PartRequest;
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
    public Part create(PartRequest request){

        Part part = PartConverter.convert(request);

        return partRepository.save(part);
    }
}
