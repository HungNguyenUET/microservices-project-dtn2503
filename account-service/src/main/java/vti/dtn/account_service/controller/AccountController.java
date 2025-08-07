package vti.dtn.account_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vti.dtn.account_service.dto.AccountDTO;
import vti.dtn.account_service.services.AccountService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @Value("${greeting.text}")
    private String greetingText;

    @GetMapping
    public List<AccountDTO> getListAccounts() {
        log.info("Fetching list of accounts");
        return accountService.getListAccounts();
    }

    @GetMapping("/greeting")
    public String greet() {
        return greetingText;
    }

}
