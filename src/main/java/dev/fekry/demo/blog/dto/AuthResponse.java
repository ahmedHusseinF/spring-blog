package dev.fekry.demo.blog.dto;

public class AuthResponse {
    String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
