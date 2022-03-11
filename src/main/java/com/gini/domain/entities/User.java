package com.gini.domain.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.UUID;

@Data
@Entity
public class User {

    private UUID id;
    private String username;

    @OneToMany(mappedBy = "user")
    private Comment comment;
}
