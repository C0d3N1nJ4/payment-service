package com.naiomi.paymentservice.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.naiomi.paymentservice.exceptions.AccountNotFoundException;


@Service
public class AccountServiceClient {

    @Value("http://localhost:9090/account")
    private String apiURL;

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

    public AccountDto updateAccount(String id, AccountDto accountDto) {
        try {
            return restTemplate.postForObject(apiURL + "/balance/" + id, accountDto, AccountDto.class);
        } catch (RestClientException e) {
            throw new AccountNotFoundException("Account with ID " + id + " not found.");
        }
    }
}
