package com.williamfeliciano.springsecurityeasybytes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@EnableWebSecurity(debug = true) to use to see chain order
public class  SpringSecurityEasyBytesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityEasyBytesApplication.class, args);
    }

}
