package com.fdosnon.online_banking.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {
    private Integer id;
    private String label;
    private String type;
    private BigDecimal amount;
}
