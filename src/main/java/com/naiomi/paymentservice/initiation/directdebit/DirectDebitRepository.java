package com.naiomi.paymentservice.initiation.directdebit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectDebitRepository extends JpaRepository<DirectDebit, String> {
}
