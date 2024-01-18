package com.naiomi.paymentservice.initiation.credittransfer;

import com.naiomi.paymentservice.account.AccountDto;
import com.naiomi.paymentservice.account.AccountServiceClient;
import com.naiomi.paymentservice.account.BalanceDto;
import com.naiomi.paymentservice.exceptions.InsufficientFundsException;
import com.naiomi.paymentservice.exceptions.PaymentNotFoundException;
import com.naiomi.paymentservice.initiation.data.PaymentStatus;
import com.naiomi.paymentservice.initiation.data.PaymentType;
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
        AccountDto debtorAccount = getAccount(creditTransfer.getDebtorAccountId());
        AccountDto creditorAccount = getAccount(creditTransfer.getCreditorAccountId());
        boolean balanceIsValid = doesAccountHaveEnoughFunds(debtorAccount, creditTransfer.getAmount());
        if (!balanceIsValid) {
            creditTransfer.setStatus(PaymentStatus.INSUFFICIENT_FUNDS);
            creditTransferRepository.save(creditTransfer);
            throw new InsufficientFundsException("Insufficient funds to complete payment");
        }
        calculateBalance(debtorAccount, creditorAccount, creditTransfer.getAmount());
        return creditTransferRepository.save(creditTransfer);
    }
//    private void IsPaymentAccountValid(String account) {
//        AccountDto accounttoValidate = getAccount(account.getId());
//        if (accounttoValidate == null) {
//            throw new AccountNotFoundException("Account id " + accounttoValidate + "not found");
//        }
//    }

    public AccountDto getAccount(String id) {
        return accountServiceClient.getAccount(id);
    }

    public void calculateBalance(AccountDto debtorAccount, AccountDto creditorAccount, double amount) {
        BalanceDto debtorBalance = new BalanceDto(debtorAccount.getBalance() - amount);
        updateAccountBalance(debtorAccount.getId(), debtorBalance);
        BalanceDto creditorBalance = new BalanceDto(creditorAccount.getBalance() + amount);
        updateAccountBalance(creditorAccount.getId(), creditorBalance);
    }

    public AccountDto updateAccountBalance(String id, BalanceDto balance) {
        return accountServiceClient.updateAccountBalance(id, balance);
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
            reversal.setStatus(PaymentStatus.REVERSED);
            creditTransferRepository.save(reversal);
            return reversal;
        } else {
            throw new PaymentNotFoundException(paymentId);
        }
    }

    public boolean doesAccountHaveEnoughFunds(AccountDto account, double amount) {
        return account.getBalance() >= amount;
    }
}
