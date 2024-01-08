package com.naiomi.paymentservice.initiation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class CreditTransferController {

    private final CreditTransferServiceImpl creditTransferService;

    public CreditTransferController(CreditTransferServiceImpl creditTransferService) {
        this.creditTransferService = creditTransferService;
    }

    @GetMapping("/credit-transfers")
    @ResponseBody
    public List<CreditTransfer> getAllCreditTransfers() {
        return creditTransferService.getAllCreditTransfers();
    }
}
