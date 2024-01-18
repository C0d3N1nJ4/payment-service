INSERT INTO Credit_Transfer (PAYMENT_ID, PAYMENT_DATE_TIME, PAYMENT_TYPE, AMOUNT, CURRENCY, DEBTOR_ACCOUNT_ID, CREDITOR_ACCOUNT_ID, REFERENCE, STATUS) VALUES
                                                                                                                                     ('123456789', '2024-01-09 07:38:24', 0, 1000.00, 'USD', 'DEBT12345', 'CRED67890', 'Payment for services', 1),
                                                                                                                                     ('987654321', '2024-01-10 08:40:30', 0, 1500.50, 'EUR', 'DEBT54321', 'CRED09876', 'Payment for goods', 0),
                                                                                                                                     ('1', '2024-01-10 08:40:30', 0, 1500.50, 'EUR', '1', '2', 'Payment for goods', 0),
                                                                                                                                     ('567890123', '2024-01-11 09:45:35', 0, 750.25, 'GBP', 'DEBT67890', 'CRED12345', 'Refund', 1);
