package com.naiomi.paymentservice.initiation.directdebit;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DirectDebitService {

    List<DirectDebit> findAll();

    Optional<DirectDebit> findById(String id);

    DirectDebit createDirectDebit(DirectDebit directDebit);

    DirectDebit update(String id, DirectDebit directDebit);

    void delete(String id);

}
