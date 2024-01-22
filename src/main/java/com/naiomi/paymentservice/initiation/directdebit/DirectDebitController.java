package com.naiomi.paymentservice.initiation.directdebit;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direct-debits")
public class DirectDebitController {

    private final DirectDebitServiceImpl directDebitService;

    public DirectDebitController(DirectDebitServiceImpl directDebitService) {
        this.directDebitService = directDebitService;
    }

    @GetMapping()
    @ResponseBody
    public List<DirectDebit> findAll() {
        return directDebitService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public DirectDebit findById(@PathVariable String id) {
        return directDebitService.findById(id).orElseThrow(() -> new RuntimeException("Direct debit not found"));
    }

    @PostMapping()
    @ResponseBody
    public DirectDebit createDirectDebit(@RequestBody DirectDebit directDebit) {
        return directDebitService.createDirectDebit(directDebit);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public DirectDebit update(@PathVariable String id, @RequestBody DirectDebit directDebit) {
        return directDebitService.update(id, directDebit);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable String id) {
        directDebitService.delete(id);
    }
}
