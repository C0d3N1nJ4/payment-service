package com.naiomi.paymentservice.initiation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditTransferRepository extends JpaRepository<CreditTransfer, String> {
}
