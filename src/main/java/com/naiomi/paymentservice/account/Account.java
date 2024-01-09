package com.naiomi.paymentservice.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Account(String accountId) {
}
