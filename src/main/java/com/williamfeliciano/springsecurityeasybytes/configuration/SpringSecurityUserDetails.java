package com.williamfeliciano.springsecurityeasybytes.configuration;


import com.williamfeliciano.springsecurityeasybytes.models.Customer;
import com.williamfeliciano.springsecurityeasybytes.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;
import java.util.List;

//@Service
@RequiredArgsConstructor
public class SpringSecurityUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customer = customerRepository.findByEmail(username);
        String password;
        List<GrantedAuthority> authorities;
        if (customer.size() == 0) {
            throw new UsernameNotFoundException("User details not found for user: " + username);
        } else {
            String userName = customer.get(0).getEmail();
            password = customer.get(0).getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
        }
        return new User(username, password, authorities);
    }

    /* @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {

        return customerRepository.existsCustomerByEmail(username);
    }
*/
}
