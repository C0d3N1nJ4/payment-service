package com.naiomi.paymentservice.initiation;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CreditTransferService {

    List<CreditTransfer> getAllCreditTransfers();

    CreditTransfer createCreditTransfer(CreditTransfer creditTransfer);

    Optional<CreditTransfer> getCreditTransferById(String paymentId);

    CreditTransfer updateCreditTransfer(CreditTransfer creditTransfer);

    void reverseCreditTransfer(String paymentId);
}
