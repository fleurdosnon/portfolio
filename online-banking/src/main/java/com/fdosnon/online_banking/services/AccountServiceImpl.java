package com.fdosnon.online_banking.services;

import com.fdosnon.online_banking.dto.AccountDTO;
import com.fdosnon.online_banking.entities.Account;
import com.fdosnon.online_banking.mappers.AccountMapper;
import com.fdosnon.online_banking.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    @Override
    public List<AccountDTO> getListAccountDTOByClientId(Integer clientId) {
        List<Account> allActiveAccountsByClientId = (List<Account>) accountRepository.getAllActiveAccountsByClientId(clientId);
        return allActiveAccountsByClientId.stream()
                .map(accountMapper::accountToAccountDTO)
                .toList();
    }
}
