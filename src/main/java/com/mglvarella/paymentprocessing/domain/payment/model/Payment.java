package com.mglvarella.paymentprocessing.domain.payment.model;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Payment {
    @Id
    private UUID id;
    @Embedded
    private Money amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private Instant createdAt;

    public void markAsApproved() {
        this.status = PaymentStatus.APPROVED;
    }

    public void markAsRejected(){
        this.status = PaymentStatus.REJECTED;
    }
}
