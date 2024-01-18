package com.naiomi.paymentservice.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccountDto(String accountId, double balance) {
    public double getBalance() {
        return balance;
    }

    public void setBalance(double v) {
    }

    public String getId() {
        return accountId;
    }
}
