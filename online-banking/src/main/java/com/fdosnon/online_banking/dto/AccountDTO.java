package com.fdosnon.online_banking.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    Integer id;
    String number;
    String type;
    BigDecimal balance;
}
