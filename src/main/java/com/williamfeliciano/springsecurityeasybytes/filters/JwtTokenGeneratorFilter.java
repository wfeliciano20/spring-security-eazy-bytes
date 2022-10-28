package com.williamfeliciano.springsecurityeasybytes.filters;

import com.williamfeliciano.springsecurityeasybytes.constants.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get the authenticated user details from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            // Create a key which will be used for signing the jwt token
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            // Create the jwt and set the issuer as our App
            String jwt = Jwts.builder().setIssuer("Eazy Bank").setSubject("JWT Token")
                    // save the username and authorities to the jwt
                    .claim("username", authentication.getName())
                    // Pass the authenticated user authorities to the jwt token
                    .claim("authorities",populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 30000000))
                    .signWith(key).compact();
            response.setHeader(SecurityConstants.JWT_HEADER,jwt);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/user");
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        // Create a set that will hold the authorities
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            // add all the autheticated user authorities
            authoritiesSet.add(authority.getAuthority());
        }
        // Use the delimiter of , to separate the authorities
        return String.join(",", authoritiesSet);
    }
}
