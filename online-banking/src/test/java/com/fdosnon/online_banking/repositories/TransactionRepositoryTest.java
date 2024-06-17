package com.fdosnon.online_banking.repositories;

import com.fdosnon.online_banking.entities.Account;
import com.fdosnon.online_banking.entities.Client;
import com.fdosnon.online_banking.entities.Transaction;
import com.fdosnon.online_banking.util.AccountType;
import com.fdosnon.online_banking.util.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(scripts = {"file:src/test/resources/sql/create_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"file:src/test/resources/sql/drop_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class TransactionRepositoryTest {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Test
    void whenTransactionIsSaved_findById_returnObject() {
        // GIVEN
        Client clientToPersist = new Client();
        clientToPersist.setUserName("newUser");
        clientToPersist.setPassword("password");
        Client clientPersisted = clientRepository.save(clientToPersist);

        Account accountToPersist = new Account();
        accountToPersist.setClient(clientPersisted);
        accountToPersist.setNumber("123456");
        accountToPersist.setType(AccountType.CHEQUE);
        accountToPersist.setBalance(BigDecimal.TEN);
        accountToPersist.setSubscriptionDate(LocalDate.now());
        Account persistedAccount = accountRepository.save(accountToPersist);

        Transaction transactionToPersist = new Transaction();
        transactionToPersist.setAccount(persistedAccount);
        transactionToPersist.setType(TransactionType.CREDIT);
        transactionToPersist.setTransactionDate(LocalDate.now());
        transactionToPersist.setLabel("label");
        transactionToPersist.setAmount(BigDecimal.valueOf(234.56));
        Transaction persistedTransaction = transactionRepository.save(transactionToPersist);
        // WHEN
        Optional<Transaction> result = transactionRepository.findById(persistedTransaction.getId());
        // THEN
        assertTrue(result.isPresent());
        assertEquals(persistedTransaction, result.get());
    }

    @Test
    void findAllActiveTransactionsByAccountId_success() {
        // GIVEN
        Client clientToPersist = new Client();
        clientToPersist.setUserName("newUser");
        clientToPersist.setPassword("password");
        Client clientPersisted = clientRepository.save(clientToPersist);

        Account accountToPersist = new Account();
        accountToPersist.setClient(clientPersisted);
        accountToPersist.setNumber("123456");
        accountToPersist.setType(AccountType.CHEQUE);
        accountToPersist.setBalance(BigDecimal.TEN);
        accountToPersist.setSubscriptionDate(LocalDate.now());
        Account persistedAccount = accountRepository.save(accountToPersist);

        Transaction transaction_1 = new Transaction();
        transaction_1.setAccount(persistedAccount);
        transaction_1.setType(TransactionType.CREDIT);
        transaction_1.setTransactionDate(LocalDate.now());
        transaction_1.setLabel("label");
        transaction_1.setAmount(BigDecimal.valueOf(234.56));
        Transaction persistedTransaction_1 = transactionRepository.save(transaction_1);

        Transaction transaction_2 = new Transaction();
        transaction_2.setAccount(persistedAccount);
        transaction_2.setType(TransactionType.DEBIT);
        transaction_2.setTransactionDate(LocalDate.now());
        transaction_2.setLabel("label_2");
        transaction_2.setAmount(BigDecimal.valueOf(12.34));
        Transaction persistedTransaction_2 = transactionRepository.save(transaction_1);
        // WHEN
        List<Transaction> result = (List<Transaction>) transactionRepository.findAllActiveTransactionsByAccountId(persistedAccount.getId());
        // THEN
        assertFalse(result.isEmpty());
        assertTrue(result.containsAll(List.of(persistedTransaction_1, persistedTransaction_2)));
    }

}