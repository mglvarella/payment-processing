package com.mglvarella.paymentprocessing.application.service;

import com.mglvarella.paymentprocessing.application.ports.IdempotencyStorage;
import com.mglvarella.paymentprocessing.domain.payment.factory.PaymentFactory;
import com.mglvarella.paymentprocessing.domain.payment.model.Payment;
import com.mglvarella.paymentprocessing.api.mapper.PaymentMapper;
import com.mglvarella.paymentprocessing.api.dto.PaymentRequestDTO;
import com.mglvarella.paymentprocessing.api.dto.PaymentResponseDTO;
import com.mglvarella.paymentprocessing.api.dto.PaymentStatusResponseDTO;
import com.mglvarella.paymentprocessing.domain.payment.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final IdempotencyStorage idempotencyStorage;

    public PaymentService(PaymentRepository paymentRepository, IdempotencyStorage idempotencyStorage) {
        this.paymentRepository = paymentRepository;
        this.idempotencyStorage = idempotencyStorage;
    }

    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequest, String idempotencyKey){

        Duration expiration = Duration.ofMinutes(60);

        var cached = idempotencyStorage.getResponse(idempotencyKey);
        if (cached.isPresent()) {
            return cached.get();
        }

        if (!idempotencyStorage.tryLock(idempotencyKey, expiration)) {
            throw new IllegalStateException("Processamento em curso ou duplicado.");
        }

        Payment payment = PaymentFactory.from(paymentRequest);

        paymentRepository.save(payment);

        PaymentResponseDTO response = PaymentMapper.toResponseDTO(payment);

        idempotencyStorage.saveResponse(idempotencyKey, response, expiration);

        return response;
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

    public PaymentStatusResponseDTO getPaymentStatus(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found: " + paymentId));

        return PaymentMapper.toStatusResponseDTO(payment);
    }
}
