package com.g5.sincereestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SaveUserController implements Initializable {
    private int userID=DatabaseCenter.signedUserID;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField currentPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField againField;
    @FXML
    private TextField phoneNumberField;

    @FXML
    private Pane passwordPane;
    @FXML
    private CheckBox passwordPaneController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameField.setText(DatabaseCenter.getUserData("first_name",userID));
        lastNameField.setText(DatabaseCenter.getUserData("last_name",userID));
        emailField.setText(DatabaseCenter.getUserData("user_email",userID));
        phoneNumberField.setText(DatabaseCenter.getUserData("phone_number",userID));
        passwordPane.setVisible(false);
    }
    @FXML
    void GoBack(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("account-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            SincereEstateApplication.stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void checkBox(ActionEvent event){
        if(passwordPaneController.isSelected()){
            passwordPane.setVisible(true);
        }
        else{
            passwordPane.setVisible(false);
        }
    }
    @FXML
    public void saveUser(ActionEvent event) {
        String password=DatabaseCenter.getUserData("user_password",userID);

        if(passwordPaneController.isSelected()){
            if(currentPasswordField.getText().isEmpty() || newPasswordField.getText().isEmpty() || againField.getText().isEmpty()){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Fill in the blanks for password change.");
                alert.show();
                return;
            }
            if(!currentPasswordField.getText().equals(password)){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Current password is not correct.");
                alert.show();
                return;
            }
            if(!againField.getText().equals(newPasswordField.getText())){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Passwords are not matched!");
                alert.show();
                return;
            }
            if(password.equals(newPasswordField.getText())){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Current password can not be equal to new password!");
                alert.show();
                return;
            }
            password=newPasswordField.getText();
        }
        if(DatabaseCenter.canUpdateUser(firstNameField.getText(),lastNameField.getText(),emailField.getText(),password,phoneNumberField.getText())){
            GoBack(event);
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Check your information!");
            alert.show();
        }
    }
}
