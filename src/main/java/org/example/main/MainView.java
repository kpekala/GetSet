package org.example.main;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class MainView {
    public TextField fieldSetCode = null;
    public Button buttonLoadSet = null;
    public ProgressIndicator indicatorLoadingSet = null;

    @FXML
    public void onButtonLoadSetClicked(Event e){
        indicatorLoadingSet.setVisible(true);

    }
}
