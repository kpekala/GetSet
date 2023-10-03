package com.kpekala.login;

import javafx.application.Platform;
import com.kpekala.data.preferences.PreferencesService;
import com.kpekala.data.rest.BricksetRestClient;

public class LoginPresenter implements LoginCallback {
    private final LoginView view;
    private final BricksetRestClient wepApi;
    private final PreferencesService preferences;

    private String userName;

    public LoginPresenter(LoginView view, BricksetRestClient wepApi, PreferencesService preferences) {
        this.view = view;
        this.wepApi = wepApi;
        this.preferences = preferences;
    }

    public void login(String name, String password){
        this.userName = name;
        wepApi.login(name,password,this);
    }

    @Override
    public void onLoginSuccessful(String hash) {
        //runLater() is needed to go back to ui thread
        Platform.runLater(() ->{
            preferences.updateUserHash(hash);
            preferences.updateUserName(this.userName);
            view.openMainStage();
        });
    }

    @Override
    public void onLoginFailed(String message) {
        //runLater() is needed to go back to ui thread
        Platform.runLater(view::showLoginFail);
    }
}
