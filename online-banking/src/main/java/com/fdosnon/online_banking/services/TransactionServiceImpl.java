package com.fdosnon.online_banking.services;

import com.fdosnon.online_banking.dto.TransactionDTO;
import com.fdosnon.online_banking.entities.Transaction;
import com.fdosnon.online_banking.mappers.AccountMapper;
import com.fdosnon.online_banking.mappers.TransactionMapper;
import com.fdosnon.online_banking.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    @Override
    public List<TransactionDTO> getListTransactionByAccountId(Integer accountId) {
        List<Transaction> allActiveTransactionsByAccountId = (List<Transaction>) transactionRepository.findAllActiveTransactionsByAccountId(accountId);
        return allActiveTransactionsByAccountId.stream().map(transactionMapper::transactionToTransactionDTO).toList();
    }
}
