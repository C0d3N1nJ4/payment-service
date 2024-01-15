package com.naiomi.paymentservice.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccountDto(String accountId, int balance) {
    public int getBalance() {
        return balance;
    }
}
