package org.example.main;

import org.example.data.FetchSetCallback;
import org.example.data.PreferencesService;
import org.example.data.RetrofitWebApi;
import org.example.data.model.SetModel;

import java.util.Map;

public class MainPresenter implements FetchSetCallback {
    private MainView view;
    private RetrofitWebApi webApi;
    private final PreferencesService preferences = new PreferencesService();

    private String hash;

    public MainPresenter(MainView view) {
        this.view = view;
        webApi = new RetrofitWebApi();
    }

    public void onStart(){
        hash = preferences.getUserHash();
        String name = preferences.getUserName();
        view.showWelcomeDialog(hash, name);
    }

    public void onQuitClicked() {
        MainApp.getInstance().quit();
    }

    public void onLogoutClicked() {
        preferences.updateUserHash("");
        preferences.updateUserName("");
        MainApp.getInstance().startLoginScene();
    }
    public void onFetchSetClicked(String id) {
        view.showLoading();
        webApi.fetchSetData(id,hash,this);
    }

    @Override
    public void onFetchSuccessful(SetModel setModel) {
        view.showSetTextData(setModel);
        view.hideLoading();
    }

    @Override
    public void onFetchError(String message) {
        view.hideLoading();
    }


}
