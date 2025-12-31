package com.mglvarella.paymentprocessing.domain.payment;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    private UUID id;
    private Double amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private LocalDateTime time;

    public void markAsApproved() {
        this.status = PaymentStatus.APPROVED;
    }

    public void markAsRejected(){
        this.status = PaymentStatus.REJECTED;
    }
}
