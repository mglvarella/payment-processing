package com.mglvarella.paymentprocessing.api.dto;

import com.mglvarella.paymentprocessing.domain.payment.model.PaymentMethod;

public record PaymentRequestDTO(
        MoneyDTO money,
        PaymentMethod method
) {}

