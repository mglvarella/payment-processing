package com.mglvarella.paymentprocessing.api.dto;

import com.mglvarella.paymentprocessing.domain.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        BigDecimal amount,
        String currency,
        PaymentMethod method
) {}

