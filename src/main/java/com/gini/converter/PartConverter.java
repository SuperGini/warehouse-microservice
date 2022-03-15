package com.gini.converter;

import com.gini.controller.request.PartRequest;
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

    public Part convert(PartRequest request) {

        Price price = getPrice(request);
        Count partCount = getCount(request);
        Suplayer suplayer = getSuplayer(request, partCount);
        CarModel carModel = getCarModel(request);

        Part part = Part.builder()
                .manufacturer(Manufacturer.valueOf(request.getManufacturer()))
                .partCount(new BigInteger(request.getPartCount()))
                .partNumber(request.getPartNumber())
                .partName(request.getPartName())
                .carModels(Set.of(carModel))
                .suplayers(List.of(suplayer))
                .suplayerPartCount(partCount)
                .price(price)
                .build();

        partCount.setPart(part);
        partCount.setSuplayer(suplayer);
        carModel.setParts(Set.of(part));

        return part;
    }

    private Count getCount(PartRequest request) {
        Count partCount = new Count();
        partCount.setSuplayerPartCount(new BigInteger(request.getPartCount()));
        return partCount;
    }

    private CarModel getCarModel(PartRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return CarModel.builder()
                .constructor(Constructor.valueOf(request.getCarModel().getConstructor()))
                .year(LocalDate.parse(request.getCarModel().getYear(), formatter))
                .engineType(request.getCarModel().getEngine())
                .model(request.getCarModel().getModel())
                .build();
    }

    private Suplayer getSuplayer(PartRequest request, Count partCount) {
        Suplayer suplayer = new Suplayer();
        suplayer.setName(request.getSuplayerName());
        suplayer.setCount(List.of(partCount));
        return suplayer;
    }

    private Price getPrice(PartRequest request) {
        Price price = new Price();
        price.setCurrency(request.getPartPrice().getCurrency());
        price.setPrice(new BigDecimal(request.getPartPrice().getPrice()));
        return price;
    }


}
