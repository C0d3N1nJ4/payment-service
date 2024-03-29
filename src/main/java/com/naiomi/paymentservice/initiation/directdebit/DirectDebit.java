package com.naiomi.paymentservice.initiation.directdebit;

import com.naiomi.paymentservice.initiation.data.PaymentStatus;
import com.naiomi.paymentservice.initiation.data.PaymentType;
import com.naiomi.paymentservice.initiation.directdebit.mandates.DebitMandate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class DirectDebit {

    @Id
    private String paymentId;
    private PaymentType paymentType;
    private String paymentDateTime;
    private double amount;
    private String currency;
    private String debtorAccountId;
    private String creditorAccountId;
    private String reference;
    private PaymentStatus status;
    @OneToOne
    private DebitMandate debitMandate;
}
