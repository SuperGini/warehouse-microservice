package com.gini.services;

import com.gini.controller.request.CreatePartRequest;
import com.gini.controller.request.UpdatePartRequest;
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

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PartServiceImpl implements PartService {

    private static final int ITEMS_ON_PAGE = 5;

    private final PartRepository partRepository;


    @Override
    @Transactional
    public CreatePartResponse create(CreatePartRequest request) {

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
    public Integer findPartsCount() {
        return partRepository.contPartNumber();
    }

    private void checkIfPartAlreadyExists(CreatePartRequest request) {
        partRepository.findByPartNumber(request.getPartNumber())
                .ifPresent(s -> {
                    throw new PartAlreadyExists("Part already exists in database. Another one can't be created");
                });
    }

    @Override
    @Transactional
    public int addParts(UpdatePartRequest partRequest) {

        UUID partId = UUID.fromString(partRequest.getPartId());
        UUID suplayerId = UUID.fromString(partRequest.getSuplayer().getSuplayerId());

        Optional<Part> part = partRepository.findPartToUpdate(partId, suplayerId);


        if (part.isPresent()) {

            Part updatePart = part.get();

            Integer partCount = updatePart.getPartCount() + Integer.parseInt(partRequest.getPartCount());

            BigInteger suplayerCount = updatePart.getSuplayerPartCount().getSuplayerPartCount()
                                                        .add(
                                                            new BigInteger(partRequest.getPartCount()));

            updatePart.setPartCount(partCount);
            updatePart.getSuplayerPartCount().setSuplayerPartCount(suplayerCount);


            partRepository.save(updatePart);


        }

        return 0;

    }

    @Override
    @Transactional
    public void updatePart(String partId, String suplayerId) {

//        UUID one = UUID.fromString(partId);
//
//        UUID two = UUID.fromString(suplayerId);
//
//        Part partx =partRepository.findPartToUpdate(one, two);

        System.out.println("xxxx");
    }
}
