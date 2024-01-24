package com.naiomi.paymentservice.initiation.directdebit;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebitMandateServiceImpl implements DebitMandateService{

    private final DebitMandateRepository debitMandateRepository;

    public DebitMandateServiceImpl(DebitMandateRepository debitMandateRepository) {
        this.debitMandateRepository = debitMandateRepository;
    }

    @Override
    public List<DebitMandate> findAllByStatus(String status) {
        return debitMandateRepository.findAllByStatus(status);
    }
}
