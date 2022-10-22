package com.williamfeliciano.springsecurityeasybytes.repository;

import com.williamfeliciano.springsecurityeasybytes.models.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {

    List<Cards> findByCustomerId(int customerId);

}
