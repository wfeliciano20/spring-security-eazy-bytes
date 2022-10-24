package com.williamfeliciano.springsecurityeasybytes.controllers;

import com.williamfeliciano.springsecurityeasybytes.models.AccountTransactions;
import com.williamfeliciano.springsecurityeasybytes.models.Customer;
import com.williamfeliciano.springsecurityeasybytes.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;
    @Autowired
    public BalanceController(AccountTransactionsRepository accountTransactionsRepository) {
        this.accountTransactionsRepository = accountTransactionsRepository;
    }

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