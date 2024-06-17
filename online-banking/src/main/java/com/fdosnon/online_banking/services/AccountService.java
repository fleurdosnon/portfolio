package com.fdosnon.online_banking.services;

import java.util.List;

public interface AccountService {
    List<String> getListAccountNamesByClientId(Integer clientId);
}
