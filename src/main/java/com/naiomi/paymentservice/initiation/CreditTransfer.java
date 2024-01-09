package com.naiomi.paymentservice.initiation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CreditTransfer {

    @Id
    private String paymentId;
    private LocalDateTime paymentDateTime;
    private double amount;
    private String currency;
    private String debtorAccountId;
    private String creditorAccountId;
    private String reference;
    private String status;

    @PrePersist
    protected void onCreate() {
        paymentDateTime = LocalDateTime.now();
    }
}
