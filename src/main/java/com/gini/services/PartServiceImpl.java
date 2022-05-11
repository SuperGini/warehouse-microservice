package com.gini.services;

import com.gini.controller.request.CreatePartRequest;
import com.gini.controller.request.UpdatePartRequest;
import com.gini.controller.response.CreatePartResponse;
import com.gini.controller.response.ListPartsResponse;
import com.gini.controller.response.base.FindPartResponse;
import com.gini.converter.AvroConvertor;
import com.gini.converter.PartConverter;
import com.gini.domain.dto.PartDto2;
import com.gini.domain.entities.Part;
import com.gini.error.handler.exceptions.PartAlreadyExists;
import com.gini.error.handler.exceptions.PartNotFoundException;
import com.gini.kafka.producer.UpdatePriceProducer;
import com.gini.repositories.PartRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PartServiceImpl implements PartService {

    private static final int ITEMS_ON_PAGE = 5;
    private final PartRepository partRepository;
    private final UpdatePriceProducer updatePriceProducer;


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

        Part part = partRepository.findPartToUpdate(partId, suplayerId)
                .orElseThrow(
                        () -> new RuntimeException("part not found"));

        Integer partCount = updatePartCount(partRequest, part);
        BigInteger suplayerCount = updateSuplayerPartCount(partRequest, part);

        part.setPartCount(partCount);
        part.getSuplayerPartCount().setSuplayerPartCount(suplayerCount);

        partRepository.save(part);

        return 0; //because I was bored:D -> I should have returned the new updated part
    }

    @Override
    @Transactional
    public FindPartResponse findPartByPartNumber(String partNumber) {

        PartDto2 part = partRepository.findPartByPartNumberVersion2(partNumber)
                .orElseThrow(() -> new PartNotFoundException("Part not found."));

        return PartConverter.convertToFindPartResponse(part);

    }

    @Override
    @Transactional
    public FindPartResponse updatePartPrice(String partNumber, String partPrice) {

        var part = findPartBy(partNumber);
        setNewPriceOnPart(partPrice, part);

        var updatedPart = partRepository.save(part);
        var partPriceUpdate = AvroConvertor.convertToKafkaMessage(part);
        updatePriceProducer.sendMessageToKafka(partPriceUpdate);

        return PartConverter.convertToFindPartResponse(updatedPart);
    }

    private void setNewPriceOnPart(String partPrice, Part part) {
        part.getPrice().setPrice(new BigDecimal(partPrice));
    }

    private Part findPartBy(String partNumber) {
        return partRepository.findPartByPartNumber(partNumber)
                .orElseThrow(() -> new PartNotFoundException("part was not found"));
    }


    private BigInteger updateSuplayerPartCount(UpdatePartRequest partRequest, Part part) {
        return part.getSuplayerPartCount().getSuplayerPartCount()
                .add(
                        new BigInteger(partRequest.getPartCount()));
    }

    private int updatePartCount(UpdatePartRequest partRequest, Part part) {
        return part.getPartCount() + Integer.parseInt(partRequest.getPartCount());
    }
}
