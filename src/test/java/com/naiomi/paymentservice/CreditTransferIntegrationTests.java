package com.naiomi.paymentservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                                     "amount": 100.00,
                                     "currency" : "EUR",
                                     "debtorAccountId": "1",
                                     "creditorAccountId": "2", 
                                     "reference": "Payment for goods",    
                                     "status": "Pending"
                                }
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    public void createCreditTransferWithInvalidDebtorAccount_StatusAccountNotFound() throws Exception {
        mockMvc.perform(post("/payments/credit-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                     "paymentId": "1",
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
}
