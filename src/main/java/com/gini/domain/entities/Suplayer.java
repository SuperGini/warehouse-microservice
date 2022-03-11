package com.gini.domain.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@Builder
@Entity
public class Suplayer {

    private UUID id;
    private String name;

    private Part part;
}
