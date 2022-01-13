package org.example.main;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainView{
    public TextField fieldSetCode = null;
    public Button buttonLoadSet = null;
    public ProgressIndicator indicatorLoadingSet = null;
    public MenuItem buttonQuit;

    private MainPresenter mainPresenter = new MainPresenter(this);

    @FXML
    public void onButtonLoadSetClicked(Event e){
        indicatorLoadingSet.setVisible(true);
    }

    public void onStart(){
        mainPresenter.onStart();
    }

    public void showWelcomeDialog(String hash, String name){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Welcome!");
        alert.setHeaderText("You are logged as " + name);
        alert.setContentText("Your hash is " + hash + ".");
        alert.show();
    }


    public void onButtonQuitClicked(ActionEvent event) {
        mainPresenter.onQuitClicked();
    }

    public void onButtonLogoutClick(ActionEvent event) {
        mainPresenter.onLogoutClicked();
    }
}
