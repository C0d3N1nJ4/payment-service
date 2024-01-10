package com.naiomi.paymentservice.initiation;

import com.naiomi.paymentservice.account.AccountDto;
import com.naiomi.paymentservice.account.AccountServiceClient;
import com.naiomi.paymentservice.exceptions.AccountNotFoundException;
import com.naiomi.paymentservice.exceptions.PaymentNotFoundException;
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
        IsPaymentAccountsValid(creditTransfer);
        return creditTransferRepository.save(creditTransfer);
    }
    private void IsPaymentAccountsValid(CreditTransfer creditTransfer) {
        AccountDto debtorAccount = getAccount(creditTransfer.getDebtorAccountId());
        if (debtorAccount == null) {
            throw new AccountNotFoundException("Account id " + creditTransfer.getDebtorAccountId() + "not found");
        }
        AccountDto creditorAccount = getAccount(creditTransfer.getCreditorAccountId());
        if (creditorAccount == null) {
            throw new AccountNotFoundException("Account id " + creditTransfer.getDebtorAccountId() + "not found");
        }
    }

    public AccountDto getAccount(String id) {
        return accountServiceClient.getAccount(id);
    }

    public Optional<CreditTransfer> getCreditTransferById(String paymentId) {
        return creditTransferRepository.findById(paymentId);
    }

    @Override
    public CreditTransfer reverseCreditTransfer(String paymentId) {
        Optional<CreditTransfer> payment = creditTransferRepository.findById(paymentId);
        CreditTransfer reversal = new CreditTransfer();
        if (payment.isPresent()) {
            reversal.setPaymentId(payment.get().getPaymentId() + "R");
            reversal.setPaymentType(PaymentType.CREDIT_TRANSFER_REVERSAL);
            reversal.setCreditorAccountId(payment.get().getDebtorAccountId());
            reversal.setDebtorAccountId(payment.get().getCreditorAccountId());
            reversal.setAmount(payment.get().getAmount());
            reversal.setCurrency(payment.get().getCurrency());
            reversal.setReference(payment.get().getReference());
            reversal.setStatus("Reversal");
            creditTransferRepository.save(reversal);
            return reversal;
        } else {
            throw new PaymentNotFoundException(paymentId);
        }
    }
}
