package com.michups.response;

public enum Response {
    SUCCESS("success"), FAILURE("failure");

    String value;

    public String getValue() {
        return value;
    }

    private Response(String value) {
        this.value = value;
    }
}
