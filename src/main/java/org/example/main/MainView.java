package org.example.main;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class MainView{
    public TextField fieldSetCode;
    public Button buttonLoadSet;
    public ProgressIndicator indicatorLoadingSet;
    public Text textSetData;

    private MainPresenter mainPresenter = new MainPresenter(this);

    @FXML
    public void onButtonLoadSetClicked(Event e){
        mainPresenter.onFetchSetClicked(fieldSetCode.getText().trim());
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
    public void showSetData(String data){
        textSetData.setText(data);
    }

    public void onButtonQuitClicked(ActionEvent event) {
        mainPresenter.onQuitClicked();
    }

    public void onButtonLogoutClick(ActionEvent event) {
        mainPresenter.onLogoutClicked();
    }

    public void showLoading(){
        indicatorLoadingSet.setVisible(true);
        buttonLoadSet.setDisable(true);
    }
    public void hideLoading(){
        indicatorLoadingSet.setVisible(false);
        buttonLoadSet.setDisable(false);
    }
}
