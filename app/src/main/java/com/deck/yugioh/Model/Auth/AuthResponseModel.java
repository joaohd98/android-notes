package com.deck.yugioh.Model.Auth;

public class AuthResponseModel {

    private String email;
    private String name;

    public AuthResponseModel() {}

    public AuthResponseModel(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}