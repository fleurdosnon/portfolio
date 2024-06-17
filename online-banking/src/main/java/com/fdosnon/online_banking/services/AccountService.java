package com.fdosnon.online_banking.services;

import com.fdosnon.online_banking.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getListAccountDTOByClientId(Integer clientId);
}
