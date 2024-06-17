package com.fdosnon.online_banking.mappers;

import com.fdosnon.online_banking.dto.AccountDTO;
import com.fdosnon.online_banking.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO accountToAccountDTO(Account account);
}
