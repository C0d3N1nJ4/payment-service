package com.naiomi.paymentservice.initiation.directdebit;

import com.naiomi.paymentservice.exceptions.PaymentNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class DirectDebitController {

    private final DirectDebitServiceImpl directDebitService;

    public DirectDebitController(DirectDebitServiceImpl directDebitService) {
        this.directDebitService = directDebitService;
    }

    @GetMapping("/direct-debits")
    public List<DirectDebit> findAll() {
        return directDebitService.findAll();
    }

    @GetMapping("/direct-debits/{id}")
    public DirectDebit findById(@PathVariable String id) {
        return directDebitService.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
    }

    @PostMapping("/direct-debits")
    public DirectDebit createDirectDebit(@RequestBody DirectDebit directDebit) {
        return directDebitService.createDirectDebit(directDebit);
    }

    @PutMapping("/{id}")
    public DirectDebit update(@PathVariable String id, @RequestBody DirectDebit directDebit) {
        return directDebitService.update(id, directDebit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        directDebitService.delete(id);
    }
}
