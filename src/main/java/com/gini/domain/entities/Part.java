package com.gini.domain.entities;

import com.gini.domain.enums.Constructor;
import com.gini.domain.enums.Manufacturer;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Part {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    private String partName;
    private String partNumber;
    private OffsetDateTime created;
    private OffsetDateTime updated;

    @OneToOne
    private Price price;

    @ManyToMany
    private Suplayer suplayer;

    @ManyToMany
    private CarModel carModel;

    @Enumerated(EnumType.STRING)
    private Constructor constructor;

    @OneToOne
    private PartSpecifications partSpecifications;

    @OneToMany(mappedBy = "part")
    private Comment comment;

    @Enumerated(EnumType.STRING)
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
