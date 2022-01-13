package org.example.login;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.main.MainApp;

public class LoginView {
    public Button buttonLoad;
    public ProgressIndicator indicatorLogin;
    public TextField fieldName;
    public PasswordField fieldPassword;

    private final LoginPresenter loginPresenter = new LoginPresenter(this);

    public void onLoginButtonClicked(MouseEvent mouseEvent) {
        String name = fieldName.getText();
        String password = fieldPassword.getText();
        loginPresenter.login(name,password);
    }

    public void initialize(){
        System.out.println("asdasdasd");
        //loginPresenter.onStart();
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
