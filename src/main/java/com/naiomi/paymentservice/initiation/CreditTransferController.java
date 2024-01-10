package com.naiomi.paymentservice.initiation;

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

    @GetMapping("/credit-transfer/{paymentId}")
    @ResponseBody
    public CreditTransfer getCreditTransferById(@PathVariable String paymentId) {
        return creditTransferService.getCreditTransferById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));
    }

    @PutMapping("/credit-transfer/reverse/{paymentId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CreditTransfer reverseCreditTransfer(@PathVariable String paymentId) {
        return creditTransferService.reverseCreditTransfer(paymentId);
    }

}
