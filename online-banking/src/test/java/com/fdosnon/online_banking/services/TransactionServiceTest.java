package com.fdosnon.online_banking.services;

import com.fdosnon.online_banking.dto.TransactionDTO;
import com.fdosnon.online_banking.entities.Transaction;
import com.fdosnon.online_banking.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    TransactionServiceImpl transactionService;
    @Mock
    TransactionRepository transactionRepository;

    @Test
    void getListTransactionByAccountId() {
        // GIVEN
        Transaction transaction_1 = new Transaction();
        transaction_1.setId(1);
        Transaction transaction_2 = new Transaction();
        transaction_2.setId(2);
        Transaction transaction_3 = new Transaction();
        transaction_3.setId(3);

        Mockito.when(transactionRepository.findAllActiveTransactionsByAccountId(13))
                .thenReturn(List.of(transaction_1, transaction_2, transaction_3));
        // WHEN
        List<TransactionDTO> result = transactionService.getListTransactionByAccountId(13);
        // THEN
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
    }
}