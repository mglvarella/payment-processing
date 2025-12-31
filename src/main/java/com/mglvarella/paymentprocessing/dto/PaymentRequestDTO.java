package com.mglvarella.paymentprocessing.dto;

import com.mglvarella.paymentprocessing.domain.payment.model.Money;
import com.mglvarella.paymentprocessing.domain.payment.model.PaymentMethod;

public record PaymentRequestDTO(
        Money amount,
        PaymentMethod method
) {}

