package com.williamfeliciano.springsecurityeasybytes.controllers;

import com.williamfeliciano.springsecurityeasybytes.models.AccountTransactions;
import com.williamfeliciano.springsecurityeasybytes.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam int id) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepository
                .findByCustomerIdOrderByTransactionDtDesc(id);
        if(accountTransactions != null) {
            return accountTransactions;
        }
        else {
            return null;
        }
    }

}