package com.fdosnon.online_banking.repositories;

import com.fdosnon.online_banking.entities.Account;
import com.fdosnon.online_banking.entities.Client;
import com.fdosnon.online_banking.entities.Transaction;
import com.fdosnon.online_banking.util.AccountType;
import com.fdosnon.online_banking.util.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@Sql(scripts = {"file:src/test/resources/sql/create_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"file:src/test/resources/sql/drop_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class TransactionRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

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
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(persistedTransaction, result.get());
    }

}