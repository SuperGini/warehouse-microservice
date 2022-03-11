package com.gini.domain.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Entity
public class Comment {

    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Part part;
}
