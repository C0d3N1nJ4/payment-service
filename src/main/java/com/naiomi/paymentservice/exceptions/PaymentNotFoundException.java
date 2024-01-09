package com.naiomi.paymentservice.exceptions;

public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException(final String paymentId) {
        super("Payment with id " + paymentId + " not found");
    }
}
