package com.naiomi.paymentservice.initiation.credittransfer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CreditTransferService {

    List<CreditTransfer> getAllCreditTransfers();

    CreditTransfer createCreditTransfer(CreditTransfer creditTransfer);

    Optional<CreditTransfer> getCreditTransferById(String paymentId);

    CreditTransfer reverseCreditTransfer(String paymentId);
}
