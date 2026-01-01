package com.mglvarella.paymentprocessing.api.dto;

import java.math.BigDecimal;

public record MoneyDTO(
        BigDecimal amount,
        String currency
) {}
