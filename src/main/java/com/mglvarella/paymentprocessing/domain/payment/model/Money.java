package com.mglvarella.paymentprocessing.domain.payment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Currency;

@Embeddable
public class Money {

    @Column(name = "amount", nullable = false)
    private BigDecimal value;

    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    protected Money() {}

    public Money(BigDecimal value, Currency currency) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.value = value;
        this.currency = currency.getCurrencyCode();
    }
}
