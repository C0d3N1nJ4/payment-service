package com.naiomi.paymentservice.initiation.directdebit;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectDebitServiceImpl implements DirectDebitService{

    private final DirectDebitRepository directDebitRepository;

    public DirectDebitServiceImpl(DirectDebitRepository directDebitRepository) {
        this.directDebitRepository = directDebitRepository;
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
        return directDebitRepository.save(directDebit);
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
