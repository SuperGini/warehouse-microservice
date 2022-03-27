package com.gini.domain.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Suplayer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    private List<Part> parts = new ArrayList<>();

    @OneToMany(mappedBy = "suplayer")
    @Column(name = "part_count")
    private List<Count> count = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suplayer suplayer = (Suplayer) o;
        return Objects.equals(id, suplayer.id) && Objects.equals(name, suplayer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
