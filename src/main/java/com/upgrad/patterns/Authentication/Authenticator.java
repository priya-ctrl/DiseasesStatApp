package com.upgrad.patterns.Authentication;

import com.upgrad.patterns.Middleware.AuthenticationProcessor;
import com.upgrad.patterns.Middleware.BasicAuthProcessor;
import com.upgrad.patterns.Middleware.JwtAuthProcessor;

import javax.servlet.http.HttpServletRequest;

public class Authenticator {

    // Public static method GetAuthProcessor of return type AuthenticationProcessor
    public static AuthenticationProcessor GetAuthProcessor() {
        // Create an object of type JwtAuthProcessor
        AuthenticationProcessor jwtProcessor = new JwtAuthProcessor();

        // Chain Authentication processors, first JWT processor is used, then BasicAuthProcessor
        AuthenticationProcessor basicProcessor = new BasicAuthProcessor();

        // Setting the next processor in the chain
        jwtProcessor.setNextProcessor(basicProcessor);

        // Return the jwtProcessor (the first in the chain)
        return jwtProcessor;
    }

    // Method to return appropriate AuthenticationProvider based on request headers
    public static AuthenticationProvider GetAuthProvider(HttpServletRequest request) {
        if (request.getHeader("Authorization") != null)
            return new JwtAuthProvider(request.getHeader("Authorization"));
        return new BasicAuthProvider(request.getHeader("Username"), request.getHeader("Password"));
    }
}
