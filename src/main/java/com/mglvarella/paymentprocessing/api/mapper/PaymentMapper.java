package com.mglvarella.paymentprocessing.api.mapper;

import com.mglvarella.paymentprocessing.api.dto.MoneyResponseDTO;
import com.mglvarella.paymentprocessing.api.dto.PaymentResponseDTO;
import com.mglvarella.paymentprocessing.api.dto.PaymentStatusResponseDTO;
import com.mglvarella.paymentprocessing.domain.payment.model.Payment;

public class PaymentMapper {
    public static PaymentResponseDTO toResponseDTO(Payment payment) {
        MoneyResponseDTO moneyDTO = new MoneyResponseDTO(
                payment.getMoney().getAmount(),
                payment.getMoney().getCurrency().getCurrencyCode()
        );

        return new PaymentResponseDTO(
                payment.getId(),
                payment.getStatus(),
                moneyDTO,
                payment.getCreatedAt()
        );
    }

    public static PaymentStatusResponseDTO toStatusResponseDTO(Payment payment){
        return new PaymentStatusResponseDTO(
                payment.getId(),
                payment.getStatus()
        );
    }
}
