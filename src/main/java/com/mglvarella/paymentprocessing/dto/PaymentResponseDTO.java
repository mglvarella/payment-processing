package com.mglvarella.paymentprocessing.dto;

import com.mglvarella.paymentprocessing.domain.payment.model.Money;
import com.mglvarella.paymentprocessing.domain.payment.model.PaymentStatus;

import java.time.Instant;
import java.util.UUID;

public record PaymentResponseDTO(
        UUID id,
        PaymentStatus status,
        Money amount,
        Instant createdAt
) {}

