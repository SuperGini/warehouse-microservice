package com.gini.domain.entities;

import com.gini.domain.enums.Constructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Entity
public class CarModel {

    private UUID id;
    private Constructor constructor;
    private String model;

    private LocalDate year;
    private String engineType;

    @ManyToMany
    private Part part;
}
