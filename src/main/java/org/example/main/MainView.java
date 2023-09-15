package org.example.main;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.data.model.SetModel;

import java.io.InputStream;

public class MainView{
    public TextField fieldSetCode;
    public Button buttonLoadSet;
    public ProgressIndicator indicatorLoadingSet;
    public Text textName;
    public Text textTheme;
    public Text textYear;
    public Text textPieceCount;
    public ImageView imageSet;

    private final MainPresenter mainPresenter = new MainPresenter(this);

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

    public void showSetTextData(SetModel setModel) {
        textName.setText("Name: " + setModel.getName());
        textPieceCount.setText("Pieces: " + setModel.getPieceCount());
        textTheme.setText("Theme: " + setModel.getTheme());
        textYear.setText("Year: " + setModel.getYear());
        System.out.println(setModel.getImageLink());
    }

    public void showSetImage(InputStream imageByteStream) {
        imageSet.setImage(new Image(imageByteStream));
    }
}
