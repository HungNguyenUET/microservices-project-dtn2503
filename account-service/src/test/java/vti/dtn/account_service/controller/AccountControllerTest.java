package vti.dtn.account_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vti.dtn.account_service.dto.AccountDTO;
import vti.dtn.account_service.services.AccountService;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private AccountService accountService;

    @BeforeEach
    void initData() {
        // Initialize test data here if needed
    }

    @Test
    void getListAccounts_validRequest_success() throws Exception {
        List<AccountDTO> mockAccounts = List.of();
        Mockito.when(accountService.getListAccounts()).thenReturn(mockAccounts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
}
