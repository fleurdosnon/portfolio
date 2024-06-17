package com.fdosnon.online_banking.mappers;

import com.fdosnon.online_banking.dto.AccountDTO;
import com.fdosnon.online_banking.entities.Account;
import com.fdosnon.online_banking.util.AccountType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountMapperTest {

    @Test
    void accountToAccountDTO_success() {
        // GIVEN
        Account account = new Account();
        account.setId(33);
        account.setNumber("123456");
        account.setType(AccountType.CHEQUE);
        account.setBalance(BigDecimal.valueOf(350.35));
        // WHEN
        AccountDTO result = AccountMapper.INSTANCE.accountToAccountDTO(account);
        // THEN
        assertNotNull(result);
        assertEquals(account.getId(), result.getId());
        assertEquals(account.getNumber(), result.getNumber());
        assertEquals("CHEQUE", result.getType());
        assertEquals(account.getBalance(), result.getBalance());
    }
}