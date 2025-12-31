package com.mglvarella.paymentprocessing.controller;

import com.mglvarella.paymentprocessing.dto.PaymentRequestDTO;
import com.mglvarella.paymentprocessing.dto.PaymentResponseDTO;
import com.mglvarella.paymentprocessing.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public ResponseEntity<PaymentResponseDTO> create(String idempotencyKey, PaymentRequestDTO requestDTO){

        PaymentResponseDTO response = paymentService.createPayment(requestDTO, idempotencyKey);

        return ResponseEntity.ok(response);
    }
}
