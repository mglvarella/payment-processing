package com.mglvarella.paymentprocessing.domain.payment.fabric;

import com.mglvarella.paymentprocessing.domain.payment.model.Money;
import com.mglvarella.paymentprocessing.domain.payment.model.Payment;
import com.mglvarella.paymentprocessing.domain.payment.model.PaymentStatus;
import com.mglvarella.paymentprocessing.dto.PaymentRequestDTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PaymentFabric {

    public static Payment from(PaymentRequestDTO request) {
        if (request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Money money = new Money(request.amount(), request.currency());

        Payment payment = new Payment(
                UUID.randomUUID(),
                money,
                request.method(),
                PaymentStatus.CREATED,
                Instant.now()
        );

        return payment;
    }
}
