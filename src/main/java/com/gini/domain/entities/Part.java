package com.gini.domain.entities;

import com.gini.domain.enums.Constructor;
import com.gini.domain.enums.Manufacturer;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.*;

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

    @NotNull
    private String partName;

    @Column(name = "part_number", nullable = false, unique = true)
    private String partNumber;

    @CreationTimestamp
    private OffsetDateTime created;

    @UpdateTimestamp
    private OffsetDateTime updated;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Price price;

    @ManyToMany(mappedBy = "parts", cascade = {CascadeType.PERSIST})
    private List<Suplayer> suplayers = new ArrayList<>();

    @ManyToMany(mappedBy = "parts", cascade = CascadeType.PERSIST)
    private Set<CarModel> carModels = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Constructor constructor;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private PartSpecification partSpecifications;

    @OneToMany(mappedBy = "part", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();

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
