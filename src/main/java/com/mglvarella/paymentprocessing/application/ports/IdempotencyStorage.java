package com.mglvarella.paymentprocessing.application.ports;

import com.mglvarella.paymentprocessing.api.dto.PaymentResponseDTO;

import java.time.Duration;
import java.util.Optional;

public interface IdempotencyStorage {
    boolean tryLock(String key, Duration timeout);
    void saveResponse(String key, PaymentResponseDTO response, Duration timeout);
    Optional<PaymentResponseDTO> getResponse(String key);
}