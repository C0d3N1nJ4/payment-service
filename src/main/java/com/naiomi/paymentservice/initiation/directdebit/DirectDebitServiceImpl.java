package com.naiomi.paymentservice.initiation.directdebit;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectDebitServiceImpl implements DirectDebitService{

    @Override
    public List<DirectDebit> findAll() {
        return null;
    }

    @Override
    public Optional<DirectDebit> findById(String id) {
        return Optional.empty();
    }

    @Override
    public DirectDebit createDirectDebit(DirectDebit directDebit) {
        return null;
    }

    @Override
    public DirectDebit update(String id, DirectDebit directDebit) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
