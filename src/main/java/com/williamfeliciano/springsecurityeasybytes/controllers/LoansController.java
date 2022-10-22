package com.williamfeliciano.springsecurityeasybytes.controllers;


import com.williamfeliciano.springsecurityeasybytes.models.Loans;
import com.williamfeliciano.springsecurityeasybytes.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoansRepository loanRepository;

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