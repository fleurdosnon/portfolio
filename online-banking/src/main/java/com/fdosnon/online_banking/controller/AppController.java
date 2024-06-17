package com.fdosnon.online_banking.controller;

import com.fdosnon.online_banking.dto.AccountDTO;
import com.fdosnon.online_banking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AppController {

    private final AccountService accountService;

    @GetMapping("/greetings")
    String greetings() {
        return "Hello";
    }

    @GetMapping("/accounts/{clientId}")
    List<AccountDTO> getListAccountsByUserId(@PathVariable Integer clientId) {
        return accountService.getListAccountDTOByClientId(clientId);
    }
}
