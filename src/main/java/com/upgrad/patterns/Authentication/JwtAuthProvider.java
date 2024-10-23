package com.upgrad.patterns.Authentication;

public class JwtAuthProvider extends AuthenticationProvider {

    private String jwtToken;

    public JwtAuthProvider(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public boolean authenticate() {
        // Implement the actual JWT token validation logic here
        // For example, decode the JWT and check its validity
        return validateJwtToken(jwtToken);
    }

    private boolean validateJwtToken(String token) {
        // Logic for validating the JWT token
        // This is a placeholder for actual token validation logic
        return token != null && !token.isEmpty();
    }
}
