package com.gini.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "part_count")
public class Count {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "suplayer_Part_Count")
    private BigInteger suplayerPartCount;

    @OneToOne(fetch = FetchType.LAZY)
    private Part part;

    @ManyToOne(fetch = FetchType.LAZY)
    private Suplayer suplayer;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count count = (Count) o;
        return Objects.equals(part, count.part) && Objects.equals(suplayer, count.suplayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(part, suplayer);
    }
}
