package com.gini.domain.entities;

import com.gini.domain.enums.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
    @Type(type = "uuid-char") // -> so we can save the uuid as a varchar in database and not binary
    private UUID id;

    @Version
    private int version;

    @Column(name = "part_name", nullable = false)
    private String partName;

    @Column(name = "part_count", nullable = false)
    private Integer partCount;

    @OneToOne(mappedBy = "part", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Count suplayerPartCount;

    @Column(name = "part_number", nullable = false, unique = true, updatable = false)
    private String partNumber;

    @CreationTimestamp
    private OffsetDateTime created;

    @UpdateTimestamp
    private OffsetDateTime updated;
    //it will break at update if no CascadeType.Merge
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private Price price;

    @ManyToMany(mappedBy = "parts", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Suplayer> suplayers = new ArrayList<>();

    @ManyToMany(mappedBy = "parts", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<CarModel> carModels = new ArrayList<>();

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
