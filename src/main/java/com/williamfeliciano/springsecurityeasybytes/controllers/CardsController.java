package com.williamfeliciano.springsecurityeasybytes.controllers;

import com.williamfeliciano.springsecurityeasybytes.models.Cards;
import com.williamfeliciano.springsecurityeasybytes.models.Customer;
import com.williamfeliciano.springsecurityeasybytes.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardsController {

    private final CardsRepository cardsRepository;
    @Autowired
    public CardsController(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam int id){
        List<Cards> cards = cardsRepository.findByCustomerId(id);
        if(cards != null){
            return cards;
        }else{
            return null;
        }
    }

}