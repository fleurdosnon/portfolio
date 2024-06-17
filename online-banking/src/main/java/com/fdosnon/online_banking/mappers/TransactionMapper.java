package com.fdosnon.online_banking.mappers;

import com.fdosnon.online_banking.dto.TransactionDTO;
import com.fdosnon.online_banking.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDTO transactionToTransactionDTO(Transaction transaction);
}
