package com.gini.converter;

import com.gini.controller.request.CreatePartRequest;
import com.gini.controller.response.CreatePartResponse;
import com.gini.controller.response.ListPartsResponse;
import com.gini.controller.response.PriceResponse;
import com.gini.controller.response.base.FindPartResponse;
import com.gini.domain.dto.PartDto;
import com.gini.domain.dto.PartDto2;
import com.gini.domain.entities.*;
import com.gini.domain.enums.Constructor;
import com.gini.domain.enums.Manufacturer;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@UtilityClass
public class PartConverter {

    public Part convert(CreatePartRequest request) {

        Price price = getPrice(request);
        Count partCount = getCount(request);
        Suplayer suplayer = getSuplayer(request, partCount);
        CarModel carModel = getCarModel(request);

        Part part = Part.builder()
                .manufacturer(Manufacturer.valueOf(request.getManufacturer()))
                .partCount(Integer.parseInt(request.getPartCount()))
                .partNumber(request.getPartNumber())
                .partName(request.getPartName())
                .carModels(List.of(carModel))
                .suplayers(List.of(suplayer))
                .suplayerPartCount(partCount)
                .price(price)
                .build();

        partCount.setPart(part);
        partCount.setSuplayer(suplayer);
        carModel.setParts(List.of(part));
        suplayer.setParts(List.of(part));

        return part;
    }

    public CreatePartResponse convertToListPartResponse(Part part){

        PriceResponse priceResponse = PriceResponse.builder()
                .price(part.getPrice().getPrice())
                .discount(part.getPrice().getDiscount())
                .currency(part.getPrice().getCurrency())
                .vat(part.getPrice().getVat())
                .build();

       return  CreatePartResponse.builder()
                .id(part.getId())
                .partName(part.getPartName())
                .partCount(part.getPartCount())
                .partNumber(part.getPartNumber())
                .price(priceResponse)
                .manufacturer(part.getManufacturer().getManufacturer())
                .build();
    }

    public ListPartsResponse convertToListPartResponse(PartDto part){

        PriceResponse priceResponse = PriceResponse.builder()
                .price(part.price())
                .discount(part.discount())
                .currency(part.currency())
                .vat(part.vat())
                .build();

       return  ListPartsResponse.builder()
                .id(part.id())
                .partName(part.partName())
                .partCount(BigInteger.valueOf(part.partCount()))
                .partNumber(part.partNumber())
                .price(priceResponse)
                .manufacturer(part.manufacturer().getManufacturer())
                .build();
    }

    public FindPartResponse convertToFindPartResponse(PartDto2 partDto2){

        return FindPartResponse.builder()
                .id(partDto2.id())
                .partName(partDto2.partName())
                .partCount(partDto2.partCount())
                .partNumber(partDto2.partNumber())
                .price(partDto2.price())
                .currency(partDto2.currency())
                .manufacturer(partDto2.manufacturer())
                .build();
    }


    private Count getCount(CreatePartRequest request) {
        Count partCount = new Count();
        partCount.setSuplayerPartCount(new BigInteger(request.getPartCount()));
        return partCount;
    }

    private CarModel getCarModel(CreatePartRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return CarModel.builder()
                .constructor(Constructor.valueOf(request.getCarModel().getConstructor()))
                .year(LocalDate.parse(request.getCarModel().getYear(), formatter))
                .engineType(request.getCarModel().getEngine())
                .model(request.getCarModel().getModel())
                .build();
    }

    private Suplayer getSuplayer(CreatePartRequest request, Count partCount) {
        Suplayer suplayer = new Suplayer();
        suplayer.setName(request.getSuplayerName());
        suplayer.setCount(List.of(partCount));
        return suplayer;
    }

    private Price getPrice(CreatePartRequest request) {
        Price price = new Price();
        price.setCurrency(request.getPartPrice().getCurrency());
        price.setPrice(new BigDecimal(request.getPartPrice().getPrice()));
        return price;
    }


}
