package com.naiomi.paymentservice.initiation.directdebit.mandates;

import com.naiomi.paymentservice.initiation.directdebit.DebitMandate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DebitMandateService {
    List<DebitMandate> findAllByStatus(String status);
}
