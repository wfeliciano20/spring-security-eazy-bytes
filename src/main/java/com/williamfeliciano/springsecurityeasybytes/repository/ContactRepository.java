package com.williamfeliciano.springsecurityeasybytes.repository;

import com.williamfeliciano.springsecurityeasybytes.models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {


}
