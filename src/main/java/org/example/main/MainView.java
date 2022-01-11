package org.example.main;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class MainView{
    public TextField fieldSetCode = null;
    public Button buttonLoadSet = null;
    public ProgressIndicator indicatorLoadingSet = null;

    private MainPresenter mainPresenter = new MainPresenter(this);

    @FXML
    public void onButtonLoadSetClicked(Event e){
        indicatorLoadingSet.setVisible(true);
    }

    public void onStart(){
        mainPresenter.onStart();
    }

    public void showWelcomeDialog(String hash){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Witamy!");
        alert.setContentText("Twój hash to " + hash + ".");
        alert.show();

    }
}
