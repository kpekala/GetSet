package org.example.main;

import org.example.data.PreferencesService;
import org.example.data.RetrofitWepApi;

public class MainPresenter {
    private MainView view;
    private RetrofitWepApi wepApi;
    private final PreferencesService preferences = new PreferencesService();

    public MainPresenter(MainView view) {
        this.view = view;
        wepApi = new RetrofitWepApi();
    }

    public void onStart(){
        String hash = preferences.getUserHash();
        view.showWelcomeDialog(hash);
    }
}
