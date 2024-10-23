package com.upgrad.patterns.Authentication;

public class BasicAuthProvider extends AuthenticationProvider {

    private String username;
    private String password;

    public BasicAuthProvider(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean authenticate() {
        // Implement the actual Basic Authentication logic here
        // For example, validate username and password against a database or service
        return validateCredentials(username, password);
    }

    private boolean validateCredentials(String username, String password) {
        // Placeholder for actual validation logic
        // Return true if the credentials are valid
        return "admin".equals(username) && "password".equals(password);
    }
}
