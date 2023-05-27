package com.g5.sincereestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    @FXML
    TextField firstNameTF;

    @FXML
    TextField lastNameTF;

    @FXML
    TextField emailTF;

    @FXML
    PasswordField passwordTF;

    @FXML
    TextField phoneTF;

    @FXML
    TextField birthdateTF;

    public void GoMainScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("main-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            SincereEstateApplication.stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SignUp(ActionEvent event) {
        if(DatabaseCenter.canCreateUser(firstNameTF.getText(), lastNameTF.getText(), emailTF.getText(), passwordTF.getText(), phoneTF.getText(), birthdateTF.getText())) {
            GoMainScene(event);
        } else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Check your information!");
            alert.show();
        }

    }
}
