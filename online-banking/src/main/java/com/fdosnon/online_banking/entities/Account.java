package com.fdosnon.online_banking.entities;

import com.fdosnon.online_banking.util.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "account_number")
    String number;
    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;
    @Column(name = "account_type")
    AccountType type;
    @Column(name = "subscription_date")
    LocalDate subscriptionDate;
    @Column(name = "subscription_end_date")
    LocalDate subscriptionEndDate;
}
