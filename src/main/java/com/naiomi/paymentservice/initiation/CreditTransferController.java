package com.naiomi.paymentservice.initiation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/credit-transfer")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CreditTransfer createCreditTransfer(@RequestBody CreditTransfer creditTransfer) {
        return creditTransferService.createCreditTransfer(creditTransfer);
    }
}
