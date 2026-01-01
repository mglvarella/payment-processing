package com.mglvarella.paymentprocessing.api.dto;

import com.mglvarella.paymentprocessing.domain.payment.model.PaymentStatus;

import java.time.Instant;
import java.util.UUID;

public record PaymentResponseDTO(
        UUID id,
        PaymentStatus status,
        MoneyResponseDTO money,
        Instant createdAt
) {}

