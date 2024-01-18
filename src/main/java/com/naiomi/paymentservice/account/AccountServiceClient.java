package com.naiomi.paymentservice.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.naiomi.paymentservice.exceptions.AccountNotFoundException;


@Service
public class AccountServiceClient {

    @Value("${account.service.url}")
    private String apiURL;

    @Value("${account.service.balance.url}")
    private String apiURLBalance;

    private final RestTemplate restTemplate;

    public AccountServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AccountDto getAccount(String id) {
        try {
            return restTemplate.getForObject(apiURL + "/" + id, AccountDto.class);
        } catch (RestClientException e) {
            throw new AccountNotFoundException("Account with ID " + id + " not found.");
        }
    }

    public AccountDto updateAccountBalance(String id, BalanceDto balance) {
        try {
            return restTemplate.postForObject(apiURLBalance + "/"+ id, balance, AccountDto.class);
        } catch (RestClientException e) {
            throw new AccountNotFoundException(id);
        }
    }
}
