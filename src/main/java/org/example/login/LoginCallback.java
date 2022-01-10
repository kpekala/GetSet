package org.example.login;

public interface LoginCallback {
    void loginSuccessful(String hash);
    void loginFailed(String message);
}
