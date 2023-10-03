package com.kpekala.login;

public interface LoginCallback {
    void onLoginSuccessful(String hash);
    void onLoginFailed(String message);
}
