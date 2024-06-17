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
    private Integer id;
    @Column(name = "username")
    private String userName;
    private String password;
    @OneToMany(mappedBy = "client")
    private Set<Account> accounts; //No joined account in this version
}
