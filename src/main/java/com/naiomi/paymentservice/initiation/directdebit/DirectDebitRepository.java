package com.naiomi.paymentservice.initiation.directdebit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectDebitRepository extends JpaRepository<DirectDebit, String> {
}
