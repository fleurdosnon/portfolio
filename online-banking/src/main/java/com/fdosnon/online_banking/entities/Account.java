package com.fdosnon.online_banking.entities;

import com.fdosnon.online_banking.util.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "account_number")
    private String number;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType type;
    private BigDecimal balance;
    @Column(name = "subscription_date")
    private LocalDate subscriptionDate;
    @Column(name = "subscription_end_date")
    private LocalDate subscriptionEndDate;
}
