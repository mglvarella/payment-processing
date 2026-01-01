package com.mglvarella.paymentprocessing.api.dto;

import java.math.BigDecimal;

public record MoneyRequestDTO(
        BigDecimal amount,
        String currency
) {}
