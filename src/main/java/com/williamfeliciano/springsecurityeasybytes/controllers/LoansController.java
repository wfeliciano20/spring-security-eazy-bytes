package com.williamfeliciano.springsecurityeasybytes.controllers;


import com.williamfeliciano.springsecurityeasybytes.models.Customer;
import com.williamfeliciano.springsecurityeasybytes.models.Loans;
import com.williamfeliciano.springsecurityeasybytes.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoansController {

    private final LoansRepository loanRepository;
    @Autowired
    public LoansController(LoansRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam int id) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if(loans != null){
            return loans;
        }else{
            return null;
        }
    }

}