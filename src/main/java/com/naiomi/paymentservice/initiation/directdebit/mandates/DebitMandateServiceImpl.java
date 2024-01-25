package com.naiomi.paymentservice.initiation.directdebit.mandates;

import com.naiomi.paymentservice.initiation.directdebit.DebitMandate;
import com.naiomi.paymentservice.initiation.directdebit.DebitMandateRepository;
import com.naiomi.paymentservice.initiation.directdebit.mandates.DebitMandateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebitMandateServiceImpl implements DebitMandateService {

    private final DebitMandateRepository debitMandateRepository;

    public DebitMandateServiceImpl(DebitMandateRepository debitMandateRepository) {
        this.debitMandateRepository = debitMandateRepository;
    }

    @Override
    public List<DebitMandate> findAllByStatus(String status) {
        return debitMandateRepository.findAllByStatus(status);
    }
}
