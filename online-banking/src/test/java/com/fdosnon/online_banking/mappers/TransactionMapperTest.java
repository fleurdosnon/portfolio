package com.fdosnon.online_banking.mappers;

import com.fdosnon.online_banking.dto.TransactionDTO;
import com.fdosnon.online_banking.entities.Transaction;
import com.fdosnon.online_banking.util.TransactionType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionMapperTest {

    @Test
    void transactionToTransactionDTO_success() {
        // GIVEN
        Transaction transaction = new Transaction();
        transaction.setId(101);
        transaction.setLabel("label");
        transaction.setType(TransactionType.CREDIT);
        transaction.setAmount(BigDecimal.valueOf(3456.78));
        // WHEN
        TransactionDTO result = TransactionMapper.INSTANCE.transactionToTransactionDTO(transaction);
        // THEN
        assertNotNull(result);
        assertEquals(transaction.getId(), result.getId());
        assertEquals(transaction.getLabel(), result.getLabel());
        assertEquals(transaction.getType().name(), result.getType());
        assertEquals(transaction.getAmount(), result.getAmount());
    }
}