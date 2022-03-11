package com.gini.domain.entities;

import lombok.Data;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@Entity
public class User {

    private UUID id;
    private String username;
}
