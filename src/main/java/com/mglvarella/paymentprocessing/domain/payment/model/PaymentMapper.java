package com.mglvarella.paymentprocessing.domain.payment.model;

import com.mglvarella.paymentprocessing.dto.PaymentResponseDTO;

public class PaymentMapper {
    public static PaymentResponseDTO toResponseDTO(Payment payment){
        return new PaymentResponseDTO(payment.getId(), payment.getStatus(), payment.getAmount(), payment.getCreatedAt());
    }
}
