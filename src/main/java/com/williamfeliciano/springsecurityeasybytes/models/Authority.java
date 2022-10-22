package com.williamfeliciano.springsecurityeasybytes.models;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
