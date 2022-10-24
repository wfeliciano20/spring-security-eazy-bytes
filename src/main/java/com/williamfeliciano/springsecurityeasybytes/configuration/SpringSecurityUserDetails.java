package com.williamfeliciano.springsecurityeasybytes.configuration;


import com.williamfeliciano.springsecurityeasybytes.models.Customer;
import com.williamfeliciano.springsecurityeasybytes.models.SecurityCustomer;
import com.williamfeliciano.springsecurityeasybytes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SpringSecurityUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Autowired
    public SpringSecurityUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customer = customerRepository.findByEmail(username);
        /*String password;
        List<GrantedAuthority> authorities;*/
        if (customer.size() == 0) {
            throw new UsernameNotFoundException("User details not found for user: " + username);
        } /*else {
            String userName = customer.get(0).getEmail();
            password = customer.get(0).getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
        }*/
        return new SecurityCustomer(customer.get(0));
    }
}
