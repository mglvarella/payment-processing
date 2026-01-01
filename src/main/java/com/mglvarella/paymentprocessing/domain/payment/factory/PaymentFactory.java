package com.mglvarella.paymentprocessing.domain.payment.factory;

import com.mglvarella.paymentprocessing.domain.payment.model.Money;
import com.mglvarella.paymentprocessing.domain.payment.model.Payment;
import com.mglvarella.paymentprocessing.domain.payment.model.PaymentStatus;
import com.mglvarella.paymentprocessing.api.dto.PaymentRequestDTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PaymentFactory {

    public static Payment from(PaymentRequestDTO request) {
        if (request.money().amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Money money = new Money(request.money().amount(), request.money().currency());

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
