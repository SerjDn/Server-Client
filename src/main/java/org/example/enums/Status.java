package org.example.enums;

public enum Status {

    CONNECT ("connected"),
    DISCONNECT ("disconnected");

    private final String state;

    Status(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
