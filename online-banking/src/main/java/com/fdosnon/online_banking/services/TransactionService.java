package com.fdosnon.online_banking.services;

import com.fdosnon.online_banking.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getListTransactionByAccountId(Integer accountId);
}
