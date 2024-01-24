package com.naiomi.paymentservice.initiation.directdebit;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DebitMandate {

    @Id
    private String id;
    private String debtorAccountId;
    private String creditorAccountId;
    private String frequency;
    private String startDate;
    private String endDate;
    private double amount;
    private String currency;
    private String collectionDay;
    private String reference;
    private String status;
}
