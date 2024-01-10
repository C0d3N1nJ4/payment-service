package com.naiomi.paymentservice.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccountDto(String accountId) {
}
