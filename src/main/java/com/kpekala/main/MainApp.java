package com.kpekala.main;

import com.kpekala.data.preferences.PreferencesService;
import com.kpekala.login.LoginView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Scene loginScene = null;
    private Scene mainScene = null;
    private Stage primaryStage = null;

    private LoginView loginView;
    private MainView mainView;

    private static MainApp instance;

    private final PreferencesService preferences = new PreferencesService();

    @Override
    public void start(Stage stage) {
        instance = this;
        primaryStage = stage;
        prepareScenes();
        if (!preferences.getUserHash().isEmpty()){
            startMainScene();
        }else{
            updateScene(loginScene);
        }
        initHandlers();
    }

    private void initHandlers() {
        primaryStage.setOnCloseRequest(event -> quit());
    }

    private void prepareScenes() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loginScene = loadScene("/login.fxml", loader);
            loginView = loader.getController();
            loader = new FXMLLoader();
            mainScene = loadScene("/scene.fxml",loader);
            mainView = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateScene(Scene scene){
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startMainScene(){
        updateScene(mainScene);
        mainView.onStart();
    }


    private Scene loadScene(String fileName, FXMLLoader loader) throws IOException {
        loader.setLocation(getClass().getResource(fileName));
        Parent content = loader.load();

        return new Scene(content);
    }

    public static void main(String[] args) {
        launch();
    }
    public static MainApp getInstance(){
        return instance;
    }

    public void quit() {
        Platform.exit();
        System.exit(0);
    }

    public void startLoginScene() {
        updateScene(loginScene);
        loginView.onStart();
    }
}
