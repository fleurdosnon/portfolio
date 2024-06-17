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
    private Integer id;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @Enumerated(EnumType.STRING)
    @Column(name="transaction_type")
    private TransactionType type;
    private String label;
    private BigDecimal amount;
    @Column(name="transaction_date")
    private LocalDate transactionDate;
    @Column(name="cancellation_date")
    private LocalDate cancellationDate;
}
