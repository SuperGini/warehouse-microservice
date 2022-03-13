package com.gini.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    private UUID id;

    @Column(name = "suplayer_Part_Count")
    private BigInteger suplayerPartCount;

    @OneToOne
    private Part part;

    @ManyToOne
    private Suplayer suplayer;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count count1 = (Count) o;
        return Objects.equals(suplayerPartCount, count1.suplayerPartCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suplayerPartCount);
    }
}
