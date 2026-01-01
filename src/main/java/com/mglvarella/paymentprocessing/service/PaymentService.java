package com.mglvarella.paymentprocessing.service;

import com.mglvarella.paymentprocessing.domain.payment.fabric.PaymentFabric;
import com.mglvarella.paymentprocessing.domain.payment.model.Payment;
import com.mglvarella.paymentprocessing.domain.payment.model.PaymentMapper;
import com.mglvarella.paymentprocessing.dto.PaymentRequestDTO;
import com.mglvarella.paymentprocessing.dto.PaymentResponseDTO;
import com.mglvarella.paymentprocessing.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequest, String idempotencyKey){

        Payment payment = PaymentFabric.from(paymentRequest);

        paymentRepository.save(payment);

        return new PaymentResponseDTO(payment.getId(), payment.getStatus(), payment.getAmount(), payment.getCreatedAt());
    }

    public PaymentResponseDTO getPaymentById(UUID paymentId){
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found: " + paymentId));

        return PaymentMapper.toResponseDTO(payment);
    }

    public List<PaymentResponseDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();

        return payments.stream()
                .map(PaymentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
