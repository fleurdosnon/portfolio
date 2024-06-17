package com.fdosnon.online_banking.services;

import com.fdosnon.online_banking.entities.Account;
import com.fdosnon.online_banking.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public List<String> getListAccountNamesByClientId(Integer clientId) {
        List<Account> allActiveAccountsByClientId = (List<Account>) accountRepository.getAllActiveAccountsByClientId(clientId);
        return List.of();
    }
}
