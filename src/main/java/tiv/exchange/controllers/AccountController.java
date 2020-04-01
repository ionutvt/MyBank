package tiv.exchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tiv.exchange.exceptions.AccountException;
import tiv.exchange.models.Account;
import tiv.exchange.services.AccountService;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{accountId}")
    public Account getAccountById(final @PathVariable("accountId") String accountId) throws AccountException {
        return accountService.findAccountById(Long.valueOf(accountId));

    }
}
