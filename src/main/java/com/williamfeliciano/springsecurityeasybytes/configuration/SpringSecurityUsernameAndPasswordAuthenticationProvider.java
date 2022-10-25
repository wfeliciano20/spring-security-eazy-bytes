package com.williamfeliciano.springsecurityeasybytes.configuration;

import com.williamfeliciano.springsecurityeasybytes.models.Authority;
import com.williamfeliciano.springsecurityeasybytes.models.Customer;
import com.williamfeliciano.springsecurityeasybytes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SpringSecurityUsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SpringSecurityUsernameAndPasswordAuthenticationProvider(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Get the user and password from authentication object

        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        // Fetch the data from db with customerRepository
        List<Customer> customer = customerRepository.findByEmail(username);
        // If there is at least one element in the list
        if(customer.size() > 0){
            // Check if the given password when hashed with password encoder is the same as the user's
            // password from the db
            if(passwordEncoder.matches(pwd, customer.get(0).getPwd())){
                return new UsernamePasswordAuthenticationToken(username,pwd,getGrantedAuthorities(customer.get(0).getAuthorities()));
            }else{
                // The password did not match
                throw new BadCredentialsException("Invalid Password");
            }
        }else{
            // There was no user with the given username in the database
            throw new BadCredentialsException("No user registered with this details");
        }

    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // We will support the username password and authentication
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
