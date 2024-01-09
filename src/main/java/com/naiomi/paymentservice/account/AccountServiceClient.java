package com.naiomi.paymentservice.account;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Account getAccount(String id) {
        try {
            return restTemplate.getForObject(apiURL + "/" + id, Account.class);
        } catch (RestClientException e) {
            throw new AccountNotFoundException("Account with ID " + id + " not found.");
        }
    }


}
