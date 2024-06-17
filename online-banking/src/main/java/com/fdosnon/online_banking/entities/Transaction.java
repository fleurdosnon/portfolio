package com.fdosnon.online_banking.entities;

import com.fdosnon.online_banking.util.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    Account account;
    @Column(name="transaction_type")
    TransactionType type;
    String label;
    BigDecimal amount;
    @Column(name="transaction_date")
    LocalDate transactionDate;
    @Column(name="cancellation_date")
    LocalDate cancellationDate;
}
