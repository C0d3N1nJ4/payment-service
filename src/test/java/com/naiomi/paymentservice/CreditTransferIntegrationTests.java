package com.naiomi.paymentservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CreditTransferIntegrationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void getAllCreditTransfers() throws Exception {
        mockMvc.perform(get("/payments/credit-transfers")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
    @Test
    public void createCreditTransfer_StatusCreated() throws Exception {
        mockMvc.perform(post("/payments/credit-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                     "paymentId": "1",
                                     "paymentType": 0,
                                     "amount": 100.00,
                                     "currency" : "EUR",
                                     "debtorAccountId": "1",
                                     "creditorAccountId": "2",
                                     "reference": "Payment for goods",
                                     "status": "Pending"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                                {
                                     "paymentId": "1",
                                     "amount": 100.00,
                                     "paymentType": "CREDIT_TRANSFER",
                                     "currency" : "EUR",
                                     "debtorAccountId": "1",
                                     "creditorAccountId": "2",
                                     "reference": "Payment for goods",
                                     "status": "Pending"
                                }
                                """));
    }

    @Test
    public void createCreditTransferWithInvalidDebtorAccount_StatusAccountNotFound() throws Exception {
        mockMvc.perform(post("/payments/credit-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                     "paymentId": "1",
                                     "paymentType": 0,
                                     "amount": 100.00,
                                     "currency" : "EUR",
                                     "debtorAccountId": "0",
                                     "creditorAccountId": "2",
                                     "reference": "Payment for goods",
                                     "status": "Pending"
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCreditTransferWithInvalidCreditorAccount_StatusAccountNotFound() throws Exception {
        mockMvc.perform(post("/payments/credit-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                     "paymentId": "6",
                                     "amount": 100.00,
                                     "currency" : "EUR",
                                     "debtorAccountId": "1",
                                     "creditorAccountId": "0",
                                     "reference": "Payment for goods",
                                     "status": "Pending"
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPaymentById_StatusOk() throws Exception {
        mockMvc.perform(get("/payments/credit-transfer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getPaymentById_StatusNotFound() throws Exception {
        mockMvc.perform(get("/payments/credit-transfer/9")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void reversePayment_StatusOk() throws Exception {
        mockMvc.perform(put("/payments/credit-transfer/reverse/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                {
                                     "paymentId": "1R",
                                     "paymentType": "CREDIT_TRANSFER_REVERSAL",
                                     "amount": 1500.50,
                                     "currency" : "EUR",
                                     "debtorAccountId": "2",
                                     "creditorAccountId": "1",
                                     "reference": "Payment for goods",
                                     "status": "Reversal"
                                }
                                """));
    }
}
