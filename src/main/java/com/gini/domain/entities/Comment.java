package com.gini.domain.entities;

import lombok.Data;
import org.springframework.boot.actuate.autoconfigure.info.ConditionalOnEnabledInfoContributor;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@Entity
public class Comment {

    private UUID id;
    private User user;
}
