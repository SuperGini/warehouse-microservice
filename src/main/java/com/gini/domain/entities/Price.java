package com.gini.domain.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@Builder
@Entity
public class Price {

    private BigDecimal price;
    private BigDecimal discount;
    private Currency currency;
    private BigDecimal vat;

}
