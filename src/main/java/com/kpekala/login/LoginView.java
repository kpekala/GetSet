package com.kpekala.login;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import com.kpekala.data.preferences.PreferencesService;
import com.kpekala.data.rest.BricksetRestClient;
import com.kpekala.main.MainApp;

public class LoginView {
    public Button buttonLoad;
    public ProgressIndicator indicatorLogin;
    public TextField fieldName;
    public PasswordField fieldPassword;

    private final LoginPresenter loginPresenter = new LoginPresenter(this, new BricksetRestClient(), new PreferencesService());

    public void onLoginButtonClicked() {
        String name = fieldName.getText();
        String password = fieldPassword.getText();
        loginPresenter.login(name, password);
    }

    public void initialize() {
    }

    public void showLoginFail() {
        System.out.println("Fail :)");
    }

    public void openMainStage() {
        MainApp.getInstance().startMainScene();
    }

    public void onStart() {
        fieldName.clear();
        fieldPassword.clear();
    }
}
