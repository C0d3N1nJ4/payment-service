package com.naiomi.paymentservice.initiation;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditTransferServiceImpl implements CreditTransferService {

    private final CreditTransferRepository creditTransferRepository;

    public CreditTransferServiceImpl(CreditTransferRepository creditTransferRepository) {
        this.creditTransferRepository = creditTransferRepository;
    }


    @Override
    public List<CreditTransfer> getAllCreditTransfers() {
        return creditTransferRepository.findAll();
    }

    public CreditTransfer createCreditTransfer(CreditTransfer creditTransfer) {
        return creditTransferRepository.save(creditTransfer);
    }

    public Optional<CreditTransfer> getCreditTransferById(String paymentId) {
        return creditTransferRepository.findById(paymentId);
    }

    public CreditTransfer updateCreditTransfer(CreditTransfer creditTransfer) {
        return creditTransferRepository.save(creditTransfer);
    }

    public void reverseCreditTransfer(String paymentId) {
        Optional<CreditTransfer> creditTransfer = creditTransferRepository.findById(paymentId);
        if (creditTransfer.isPresent()) {
            CreditTransfer reversal = creditTransfer.get();
            reversal.setStatus("REVERSED");
            creditTransferRepository.save(reversal);
        }
    }
}
