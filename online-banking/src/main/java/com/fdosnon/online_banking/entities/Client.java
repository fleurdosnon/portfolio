package com.fdosnon.online_banking.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "username")
    String userName;
    String password;
    @OneToMany(mappedBy = "client")
    Set<Account> accounts; //No joined accounts in this version
}
