package com.mglvarella.paymentprocessing.dto;

import com.mglvarella.paymentprocessing.domain.payment.model.PaymentStatus;

import java.util.UUID;

public record PaymentStatusResponseDTO(
        UUID paymentId,
        PaymentStatus status
) {
}
