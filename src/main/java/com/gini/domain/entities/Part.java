package com.gini.domain.entities;

import com.gini.domain.enums.Constructor;
import com.gini.domain.enums.Manufacturer;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Part {

    private UUID id;
    private String partName;
    private String partNumber;
    private OffsetDateTime created;
    private OffsetDateTime updated;
    private Price price;
    private Suplayer suplayer;
    private CarModel carModel;
    private Constructor constructor;
    private PartSpecifications partSpecifications;
    private Comment comment;
    private Manufacturer manufacturer;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return Objects.equals(partNumber, part.partNumber);
    }

    @Override
    public int hashCode() {
        return partNumber != null ? partNumber.hashCode() : 0;
    }
}
