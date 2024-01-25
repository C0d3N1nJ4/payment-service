package com.naiomi.paymentservice.initiation.directdebit;

import com.naiomi.paymentservice.initiation.directdebit.mandates.DebitMandateServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DebitMandateScheduledTask {

    private final DebitMandateServiceImpl debitMandateService;

    private final DirectDebitServiceImpl directDebitService;

    public DebitMandateScheduledTask(DebitMandateServiceImpl debitMandateService, DirectDebitServiceImpl directDebitService) {
        this.debitMandateService = debitMandateService;
        this.directDebitService = directDebitService;
    }

    @Scheduled(fixedRate = 100000)
    public void fetchActiveMandates() {
        debitMandateService.findAllByStatus("ACTIVE")
                .forEach(debitMandate -> {
                    DirectDebit directDebit = new DirectDebit();
                    directDebit.setDebitMandate(debitMandate);
                    directDebit.setAmount(debitMandate.getAmount());
                    directDebit.setCurrency(debitMandate.getCurrency());
                    directDebit.setDebtorAccountId(debitMandate.getDebtorAccountId());
                    directDebit.setCreditorAccountId(debitMandate.getCreditorAccountId());

                    directDebitService.createDirectDebit(directDebit);

                });
    }
}
