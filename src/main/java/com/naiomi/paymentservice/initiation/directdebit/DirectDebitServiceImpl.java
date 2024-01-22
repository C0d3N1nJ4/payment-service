package com.naiomi.paymentservice.initiation.directdebit;

import com.naiomi.paymentservice.account.AccountDto;
import com.naiomi.paymentservice.account.AccountServiceClient;
import com.naiomi.paymentservice.account.BalanceDto;
import com.naiomi.paymentservice.exceptions.InsufficientFundsException;
import com.naiomi.paymentservice.initiation.data.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectDebitServiceImpl implements DirectDebitService{

    private final DirectDebitRepository directDebitRepository;

    private final AccountServiceClient accountServiceClient;

    public DirectDebitServiceImpl(DirectDebitRepository directDebitRepository, AccountServiceClient accountServiceClient) {
        this.directDebitRepository = directDebitRepository;
        this.accountServiceClient = accountServiceClient;
    }

    @Override
    public List<DirectDebit> findAll() {
        return directDebitRepository.findAll();
    }

    @Override
    public Optional<DirectDebit> findById(String id) {
        return directDebitRepository.findById(id);
    }

    @Override
    public DirectDebit createDirectDebit(DirectDebit directDebit) {
        AccountDto debtorAccount = getAccount(directDebit.getDebtorAccountId());
        AccountDto creditorAccount = getAccount(directDebit.getCreditorAccountId());
        boolean balanceIsValid = doesAccountHaveEnoughFunds(debtorAccount, directDebit.getAmount());
        if (!balanceIsValid) {
            directDebit.setStatus(PaymentStatus.INSUFFICIENT_FUNDS);
            directDebitRepository.save(directDebit);
            throw new InsufficientFundsException("Insufficient funds to complete payment");
        }
        calculateBalance(debtorAccount, creditorAccount, directDebit.getAmount());

        return directDebitRepository.save(directDebit);
    }

    public AccountDto getAccount(String id) {
        return accountServiceClient.getAccount(id);
    }


    public void calculateBalance(AccountDto debtorAccount, AccountDto creditorAccount, double amount) {
        BalanceDto debtorBalance = new BalanceDto(debtorAccount.getBalance() - amount);
        accountServiceClient.updateAccountBalance(debtorAccount.getId(), debtorBalance);
        BalanceDto creditorBalance = new BalanceDto(creditorAccount.getBalance() + amount);
        accountServiceClient.updateAccountBalance(creditorAccount.accountId(), creditorBalance);
    }

    public boolean doesAccountHaveEnoughFunds(AccountDto account, double amount) {
        return account.getBalance() >= amount;
    }

    @Override
    public DirectDebit update(String id, DirectDebit directDebit) {
        return directDebitRepository.save(directDebit);
    }

    @Override
    public void delete(String id) {
        directDebitRepository.deleteById(id);
    }


}
