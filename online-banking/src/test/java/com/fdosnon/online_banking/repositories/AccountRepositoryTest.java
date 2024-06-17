package com.fdosnon.online_banking.repositories;

import com.fdosnon.online_banking.entities.Account;
import com.fdosnon.online_banking.entities.Client;
import com.fdosnon.online_banking.util.AccountType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@Sql(scripts = {"file:src/test/resources/sql/create_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"file:src/test/resources/sql/drop_tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class AccountRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;


    @Test
    void whenAccountIsSaved_findById_returnObject() {
        // GIVEN
        Client clientToPersist = new Client();
        clientToPersist.setUserName("newUser");
        clientToPersist.setPassword("password");
        Client clientPersisted = clientRepository.save(clientToPersist);
        Account accountToPersist = new Account();
        accountToPersist.setClient(clientPersisted);
        accountToPersist.setNumber("123456");
        accountToPersist.setType(AccountType.CHEQUE);
        accountToPersist.setBalance(BigDecimal.valueOf(3407.56));
        accountToPersist.setSubscriptionDate(LocalDate.now());
        Account persistedAccount = accountRepository.save(accountToPersist);
        // WHEN
        Optional<Account> result = accountRepository.findById(persistedAccount.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(persistedAccount, result.get());
    }

    @Test
    void getAllActiveAccountsByClientId() {
        // GIVEN
        Client clientToPersist = new Client();
        clientToPersist.setUserName("newUser");
        clientToPersist.setPassword("password");
        Client clientPersisted = clientRepository.save(clientToPersist);

        Account accountToPersist_1 = new Account();
        accountToPersist_1.setClient(clientPersisted);
        accountToPersist_1.setNumber("123456");
        accountToPersist_1.setType(AccountType.CHEQUE);
        accountToPersist_1.setBalance(BigDecimal.valueOf(6490.22));
        accountToPersist_1.setSubscriptionDate(LocalDate.now());
        Account persistedAccount_1 = accountRepository.save(accountToPersist_1);

        Account accountToPersist_2 = new Account();
        accountToPersist_2.setClient(clientPersisted);
        accountToPersist_2.setNumber("123456");
        accountToPersist_2.setType(AccountType.SAVINGS);
        accountToPersist_2.setBalance(BigDecimal.valueOf(35500.00));
        accountToPersist_2.setSubscriptionDate(LocalDate.now());
        Account persistedAccount_2 = accountRepository.save(accountToPersist_2);
        // WHEN
        List<Account> result = (List<Account>) accountRepository.getAllActiveAccountsByClientId(clientPersisted.getId());
        //THEN
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertTrue(result.containsAll(List.of(persistedAccount_1, persistedAccount_2)));
    }
}