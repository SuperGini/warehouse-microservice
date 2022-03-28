package com.gini.services;

import com.gini.controller.request.PartRequest;
import com.gini.controller.response.CreatePartResponse;
import com.gini.controller.response.ListPartsResponse;
import com.gini.converter.PartConverter;
import com.gini.domain.entities.Part;
import com.gini.error.handler.exceptions.PartAlreadyExists;
import com.gini.repositories.PartRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PartServiceImpl implements PartService {

    private static final int ITEMS_ON_PAGE = 5;

    private final PartRepository partRepository;


    @Override
    @Transactional
    public CreatePartResponse create(PartRequest request) {

        checkIfPartAlreadyExists(request);

        Part part = PartConverter.convert(request);

        Part savedPart = partRepository.save(part);

        return PartConverter.convertToListPartResponse(savedPart);
    }


    @Override
    @Transactional
    public List<ListPartsResponse> findAllPartsWithPagination(String pageNumber) {

        int pageNr = Integer.parseInt(pageNumber) - 1;

        Pageable pageCount = PageRequest.of(pageNr, ITEMS_ON_PAGE);

        return partRepository.findAllPartsWithPagination(pageCount)
                .stream()
                .map(PartConverter::convertToListPartResponse)
                .toList();
    }

    @Override
    @Transactional
    public Integer findPartsCount(){
        return partRepository.contPartNumber();
    }

    private void checkIfPartAlreadyExists(PartRequest request) {
        partRepository.findByPartNumber(request.getPartNumber())
                .ifPresent(s -> {
                    throw new PartAlreadyExists("Part already exists in database. Another one can't be created");
                });
    }
}
