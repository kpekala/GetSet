package org.example.data.model;

public class LoginResponse {
    private String hash;
    private String status;

    public LoginResponse(String hash, String status) {
        this.hash = hash;
        this.status = status;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
