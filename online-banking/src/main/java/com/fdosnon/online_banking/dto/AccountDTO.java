package com.fdosnon.online_banking.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Integer id;
    private String number;
    private String type;
    private BigDecimal balance;
}
