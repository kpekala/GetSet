package org.example.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.data.PreferencesService;

import java.io.IOException;

import static javafx.application.Application.launch;

public class MainApp extends Application {

    private Scene loginScene = null;
    private Scene mainScene = null;
    private Stage primaryStage = null;

    private static MainApp instance;

    private final PreferencesService preferences = new PreferencesService();

    @Override
    public void start(Stage stage) {
        instance = this;
        primaryStage = stage;
        prepareScenes();

        if (!preferences.getUserHash().isEmpty()){
            System.out.println(preferences.getUserHash());
            updateScene(mainScene);
        }else{
            updateScene(loginScene);
        }
        initHandlers();
    }

    private void initHandlers() {
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private void prepareScenes() {
        try {
            loginScene = loadScene("/login.fxml");
            mainScene = loadScene("/scene.fxml");
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
    }


    private Scene loadScene(String fileName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
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
}
