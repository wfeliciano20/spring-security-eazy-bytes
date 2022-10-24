package com.williamfeliciano.springsecurityeasybytes.controllers;

import com.williamfeliciano.springsecurityeasybytes.models.Contact;
import com.williamfeliciano.springsecurityeasybytes.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Random;

@RestController
public class ContactController {

    private final ContactRepository contactRepository;
    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("/contact")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }

    public String getServiceReqNumber(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(999999999 - 9999) + 9999;
        return "SR"+randomNumber;
    }

}