package com.naiomi.paymentservice;

import com.naiomi.paymentservice.initiation.directdebit.mandates.DebitMandate;
import com.naiomi.paymentservice.initiation.directdebit.mandates.DebitMandateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DebitMandateUnitTests {

    @Autowired
    DebitMandateRepository debitMandateRepository;

    @Test
    void getAllDebitMandates() {
        assert (debitMandateRepository.findAll().size() > 0);
    }

    @Test
    void getDebitMandateById() {
        assert (debitMandateRepository.findById("1").isPresent());
    }

    @Test
    void getDebitMandateByStatus() {
        assert (debitMandateRepository.findAllByStatus("ACTIVE").size() > 0);
    }

    @Test
    void saveDebitMandate() {
        DebitMandate debitMandate = new DebitMandate();
        debitMandate.setId("10");
        debitMandate.setDebtorAccountId("1");
        debitMandate.setCreditorAccountId("2");
        debitMandate.setFrequency("MONTHLY");
        debitMandate.setStartDate("2021-01-01");
        debitMandate.setEndDate("2021-12-31");
        debitMandate.setAmount(10.00);
        debitMandate.setCurrency("GBP");
        debitMandate.setCollectionDay("25");
        debitMandate.setReference("Rent");
        debitMandate.setStatus("ACTIVE");
        debitMandateRepository.save(debitMandate);

        assert(debitMandateRepository.findById("10").isPresent());
    }


}
