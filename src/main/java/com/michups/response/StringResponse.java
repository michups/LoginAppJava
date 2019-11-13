package com.michups.response;

public class StringResponse {

    private String response;

    private String secret;

    public StringResponse(String response, String secret) {
        this.response = response;
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
