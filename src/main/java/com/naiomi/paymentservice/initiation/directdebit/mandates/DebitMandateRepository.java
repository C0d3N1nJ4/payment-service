package com.naiomi.paymentservice.initiation.directdebit.mandates;

import com.naiomi.paymentservice.initiation.directdebit.mandates.DebitMandate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebitMandateRepository extends JpaRepository<DebitMandate, String> {

    List<DebitMandate> findAllByStatus(String status);
}
