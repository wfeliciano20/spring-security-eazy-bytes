package com.williamfeliciano.springsecurityeasybytes.controllers;


import com.williamfeliciano.springsecurityeasybytes.models.Accounts;
import com.williamfeliciano.springsecurityeasybytes.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AccountController {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam int id) {
        Accounts accounts = accountRepository.findByCustomerId(id);
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }
    }

}