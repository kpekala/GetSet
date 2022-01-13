package org.example.login;

import javafx.application.Platform;
import org.example.data.PreferencesService;
import org.example.data.RetrofitWepApi;

public class LoginPresenter implements LoginCallback {
    private LoginView view;
    private RetrofitWepApi wepApi;
    private final PreferencesService preferences = new PreferencesService();

    private String userName;

    public LoginPresenter(LoginView view) {
        this.view = view;
        wepApi = new RetrofitWepApi();
    }

    public void login(String name, String password){
        this.userName = name;
        wepApi.login(name,password,this);
    }

    @Override
    public void loginSuccessful(String hash) {
        //runLater() is needed to go back to ui thread
        Platform.runLater(() ->{
            preferences.updateUserHash(hash);
            preferences.updateUserName(this.userName);
            view.openMainStage();
        });
    }

    @Override
    public void loginFailed(String message) {
        //runLater() is needed to go back to ui thread
        Platform.runLater(() -> view.showLoginFail());
    }
}
