package com.fdosnon.online_banking.controller;

import com.fdosnon.online_banking.dto.AccountDTO;
import com.fdosnon.online_banking.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AppController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService accountService;

    @Test
    void getGreetings() throws Exception {
        // WHEN
        this.mockMvc.perform(get("/api/greetings"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }

    @Test
    void getListAccountsByUserId_shouldReturnList() throws Exception {
        // GIVEN
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1);
        accountDTO.setType("CHEQUE");
        accountDTO.setNumber("333 444 555");
        when(accountService.getListAccountDTOByClientId(42)).thenReturn(List.of(accountDTO));
        // WHEN
        this.mockMvc.perform(get("/api/client/{clientId}", 42))
                // THEN
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("333 444 555")));
    }

}