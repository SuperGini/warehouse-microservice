package com.gini.converter;

import com.gini.avro.data.PartPriceUpdate;
import com.gini.domain.entities.Part;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AvroConvertor {

    public PartPriceUpdate convertToKafkaMessage(Part part){

        return PartPriceUpdate.newBuilder()
                .setPartId(part.getId().toString())
                .setPartName(part.getPartName())
                .setPartNumber(part.getPartNumber())
                .setPrice(part.getPrice().getPrice().toString())
                .setCurrency(part.getPrice().getCurrency())
                .setManufacturer(part.getManufacturer().toString())
                .build();

    }
}
