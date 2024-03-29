package com.deck.notes.Model.Auth;

import com.deck.notes.Components.InputView;

public class AuthRequestModel {

    private String email;
    private String password;

    public AuthRequestModel() {}

    public AuthRequestModel(InputView email, InputView password) {
        this.email = email.getInputValue();
        this.password = password.getInputValue();
    }

    public AuthRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
