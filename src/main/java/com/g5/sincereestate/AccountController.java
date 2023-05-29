package com.g5.sincereestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    private int userID=DatabaseCenter.signedUserID;
    @FXML
    private Label firstNameField;
    @FXML
    private Label secondNameField;
    @FXML
    private Label emailField;
    @FXML
    private Label passwordField;
    @FXML
    private Label phoneNumberField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            firstNameField.setText(DatabaseCenter.getUserData("first_name",userID));
            secondNameField.setText(DatabaseCenter.getUserData("last_name",userID));
            emailField.setText(DatabaseCenter.getUserData("user_email",userID));
            passwordField.setText(DatabaseCenter.getUserData("user_password",userID));
            phoneNumberField.setText(DatabaseCenter.getUserData("phone_number",userID));
    }

    @FXML
    public void GoBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("homepage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            SincereEstateApplication.stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void GoUpdatePage(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("editaccount-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            SincereEstateApplication.stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
