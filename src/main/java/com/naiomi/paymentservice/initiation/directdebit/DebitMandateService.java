package com.naiomi.paymentservice.initiation.directdebit;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DebitMandateService {
    List<DebitMandate> findAllByStatus(String status);
}
