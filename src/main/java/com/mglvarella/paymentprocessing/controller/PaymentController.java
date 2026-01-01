package com.mglvarella.paymentprocessing.controller;

import com.mglvarella.paymentprocessing.dto.PaymentRequestDTO;
import com.mglvarella.paymentprocessing.dto.PaymentResponseDTO;
import com.mglvarella.paymentprocessing.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> create(@RequestHeader String idempotencyKey, @RequestBody PaymentRequestDTO requestDTO){

        PaymentResponseDTO response = paymentService.createPayment(requestDTO, idempotencyKey);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> findById(@PathVariable("id") UUID paymentId){

        PaymentResponseDTO response = paymentService.getPaymentById(paymentId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> findById(){

        List<PaymentResponseDTO> response = paymentService.getAllPayments();

        return ResponseEntity.ok(response);
    }
}
