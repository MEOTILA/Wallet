package org.example.entity;

import lombok.Data;
import org.example.enumeration.TransactionType;
@Data

public class Transaction {
    private int walletId;
    private int transactionId;
    private double amount;
    private TransactionType type;
}
