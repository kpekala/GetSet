package org.example.login;

import javafx.application.Platform;
import org.example.data.PreferencesService;
import org.example.data.RetrofitWepApi;

import java.util.prefs.Preferences;

public class LoginPresenter implements LoginCallback {
    private LoginView view;
    private RetrofitWepApi wepApi;
    private final PreferencesService preferences = new PreferencesService();

    public LoginPresenter(LoginView view) {
        this.view = view;
        wepApi = new RetrofitWepApi();
    }

    public void login(String name, String password){
        wepApi.login(name,password,this);
    }

    @Override
    public void loginSuccessful(String hash) {
        //runLater() is needed to go back to ui thread
        Platform.runLater(() ->{
            preferences.updateUserHash(hash);
            view.openMainStage();
        });
    }

    @Override
    public void loginFailed(String message) {
        //runLater() is needed to go back to ui thread
        Platform.runLater(() -> view.showLoginFail());
    }
}
