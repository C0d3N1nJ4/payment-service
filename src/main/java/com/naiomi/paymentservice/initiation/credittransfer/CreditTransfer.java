package com.naiomi.paymentservice.initiation.credittransfer;

import com.naiomi.paymentservice.initiation.data.PaymentStatus;
import com.naiomi.paymentservice.initiation.data.PaymentType;
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
    private PaymentType paymentType;
    private LocalDateTime paymentDateTime;
    private double amount;
    private String currency;
    private String debtorAccountId;
    private String creditorAccountId;
    private String reference;
    private PaymentStatus status;

    @PrePersist
    protected void onCreate() {
        paymentDateTime = LocalDateTime.now();
    }
}
