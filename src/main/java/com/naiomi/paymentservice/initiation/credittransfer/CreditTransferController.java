package com.naiomi.paymentservice.initiation.credittransfer;

import com.naiomi.paymentservice.exceptions.PaymentNotFoundException;
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
    public List<CreditTransfer> getAllCreditTransfers() {
        return creditTransferService.getAllCreditTransfers();
    }

    @PostMapping("/credit-transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditTransfer createCreditTransfer(@RequestBody CreditTransfer creditTransfer) {
        return creditTransferService.createCreditTransfer(creditTransfer);
    }

    @GetMapping("/credit-transfer/{paymentId}")
    public CreditTransfer getCreditTransferById(@PathVariable String paymentId) {
        return creditTransferService.getCreditTransferById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));
    }

    @PutMapping("/credit-transfer/reverse/{paymentId}")
    @ResponseStatus(HttpStatus.OK)
    public CreditTransfer reverseCreditTransfer(@PathVariable String paymentId) {
        return creditTransferService.reverseCreditTransfer(paymentId);
    }

}
