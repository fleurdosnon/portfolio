package com.fdosnon.online_banking.controller;

import com.fdosnon.online_banking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequiredArgsConstructor
@RestController("/api")
public class AppController {

    private AccountService accountService;

/*
    @GetMapping("/login")
    public String login() {
        return "Please log in";
    }
*/

    @GetMapping("/accounts/{clientId}")
    Set<String> getListAccountsByUserId(@PathVariable Long id) {
        return null;
    }
}
