package com.mglvarella.paymentprocessing.api.dto;

import java.math.BigDecimal;

public record MoneyResponseDTO(
        BigDecimal amount,
        String currency
) {
}
