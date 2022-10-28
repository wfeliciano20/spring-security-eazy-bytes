package com.williamfeliciano.springsecurityeasybytes.constants;

public interface SecurityConstants {

    public static final String JWT_KEY = System.getenv("JWT_SECRET");
    public static final String JWT_HEADER = "Authorization";

}