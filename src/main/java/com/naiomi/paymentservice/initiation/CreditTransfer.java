package com.naiomi.paymentservice.initiation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String debtorId;
    private String debtorAccount;
    private String creditorId;
    private String creditorAccount;
    private String status;

}
