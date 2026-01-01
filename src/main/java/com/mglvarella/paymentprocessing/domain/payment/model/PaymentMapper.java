package com.mglvarella.paymentprocessing.domain.payment.model;

import com.mglvarella.paymentprocessing.api.dto.PaymentResponseDTO;
import com.mglvarella.paymentprocessing.api.dto.PaymentStatusResponseDTO;

public class PaymentMapper {
    public static PaymentResponseDTO toResponseDTO(Payment payment){
        return new PaymentResponseDTO(payment.getId(), payment.getStatus(), payment.getAmount(), payment.getCreatedAt());
    }

    public static PaymentStatusResponseDTO toStatusResponseDTO(Payment payment){
        return new PaymentStatusResponseDTO(payment.getId(), payment.getStatus());
    }
}
