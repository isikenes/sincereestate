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

public class LoginController {
    @FXML
    TextField emailTF;

    @FXML
    PasswordField passwordTF;

    public void GoMainScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("main-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoHomePageScene(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("homepage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CheckLogin(ActionEvent event) {
        if(DatabaseCenter.isLoginInfoTrue(emailTF.getText(), passwordTF.getText())) {
            GoHomePageScene(event);
        } else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Check your information!");
            alert.show();
        }
    }

}
