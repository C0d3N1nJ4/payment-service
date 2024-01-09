package com.naiomi.paymentservice.initiation;

import com.naiomi.paymentservice.account.Account;
import com.naiomi.paymentservice.account.AccountServiceClient;
import com.naiomi.paymentservice.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditTransferServiceImpl implements CreditTransferService {

    private final CreditTransferRepository creditTransferRepository;

    private final AccountServiceClient accountServiceClient;

    public CreditTransferServiceImpl(CreditTransferRepository creditTransferRepository, AccountServiceClient accountServiceClient) {
        this.creditTransferRepository = creditTransferRepository;
        this.accountServiceClient = accountServiceClient;
    }


    @Override
    public List<CreditTransfer> getAllCreditTransfers() {
        return creditTransferRepository.findAll();
    }

    @Override
    public CreditTransfer createCreditTransfer(CreditTransfer creditTransfer) {
        isAccountValid(creditTransfer);
        return creditTransferRepository.save(creditTransfer);
    }
    private void isAccountValid(CreditTransfer creditTransfer) {
        Account debtorAccount = getAccount(creditTransfer.getDebtorAccountId());
        if (debtorAccount == null) {
            throw new AccountNotFoundException("Account id " + debtorAccount + "not found");
        }
        Account creditorAccount = getAccount(creditTransfer.getCreditorAccountId());
        if (creditorAccount == null) {
            throw new AccountNotFoundException("Account id " + creditorAccount + "not found");
        }
    }

    public Account getAccount(String id) {
        return accountServiceClient.getAccount(id);
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
