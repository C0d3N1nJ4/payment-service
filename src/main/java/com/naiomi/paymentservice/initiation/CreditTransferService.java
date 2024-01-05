package com.naiomi.paymentservice.initiation;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CreditTransferService {

    CreditTransfer createCreditTransfer(CreditTransfer creditTransfer);

    Optional<CreditTransfer> getCreditTransferById(String paymentId);

    CreditTransfer updateCreditTransfer(CreditTransfer creditTransfer);

    void reverseCreditTransfer(String paymentId);
}
