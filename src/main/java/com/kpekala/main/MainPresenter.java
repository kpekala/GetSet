package com.kpekala.main;

import com.kpekala.data.model.SetModel;
import com.kpekala.data.rest.FetchImageCallback;
import com.kpekala.data.rest.FetchSetCallback;
import com.kpekala.data.preferences.PreferencesService;
import com.kpekala.data.rest.BricksetRestClient;

import java.io.InputStream;

public class MainPresenter implements FetchSetCallback, FetchImageCallback {
    private final MainView view;
    private final BricksetRestClient webApi;
    private final PreferencesService preferences;

    private String hash;

    public MainPresenter(MainView view, BricksetRestClient webApi, PreferencesService preferences) {
        this.view = view;
        this.webApi = webApi;
        this.preferences = preferences;
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

        //Now we are focusing on loading an image of set
        webApi.fetchImageSet(setModel.imageLink(),this);

    }

    @Override
    public void onFetchError(String message) {
        view.hideLoading();
    }


    @Override
    public void onFetchImageSuccessful(InputStream imageByteStream) {
        view.showSetImage(imageByteStream);
    }

    @Override
    public void onFetchImageFailed() {

    }
}
