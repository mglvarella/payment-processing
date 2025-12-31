package com.mglvarella.paymentprocessing.service;

import com.mglvarella.paymentprocessing.domain.payment.fabric.PaymentFabric;
import com.mglvarella.paymentprocessing.domain.payment.model.Payment;
import com.mglvarella.paymentprocessing.dto.PaymentRequestDTO;
import com.mglvarella.paymentprocessing.dto.PaymentResponseDTO;
import com.mglvarella.paymentprocessing.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponseDTO createPayment(PaymentRequestDTO request, String idempotencyKey){

        Payment payment = PaymentFabric.from(request);

        paymentRepository.save(payment);

        return new PaymentResponseDTO(payment.getId(), payment.getStatus(), payment.getAmount(), payment.getCreatedAt());

    }
}
