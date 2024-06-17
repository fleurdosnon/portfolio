package com.fdosnon.online_banking.services;

import com.fdosnon.online_banking.dto.AccountDTO;
import com.fdosnon.online_banking.entities.Account;
import com.fdosnon.online_banking.repositories.AccountRepository;
import com.fdosnon.online_banking.util.AccountType;
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
class AccountServiceTest {

    @InjectMocks
    AccountServiceImpl accountService;
    @Mock
    AccountRepository accountRepository;

    @Test
    void getListAccountDTOByClientId() {
        // GIVEN
        Account account_1 = new Account();
        account_1.setId(11);
        account_1.setNumber("123456");
        account_1.setType(AccountType.CHEQUE);

        Account account_2 = new Account();
        account_2.setId(22);
        account_2.setNumber("7891011");
        account_2.setType(AccountType.CREDIT_CARD);

        Mockito.when(accountRepository.getAllActiveAccountsByClientId(42)).thenReturn(List.of(account_1, account_2));
        // WHEN
        List<AccountDTO> result = accountService.getListAccountDTOByClientId(42);
        // THEN
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }
}