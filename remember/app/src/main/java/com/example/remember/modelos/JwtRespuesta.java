package com.example.remember.modelos;

public class JwtRespuesta {
    private String token;

    public JwtRespuesta(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
