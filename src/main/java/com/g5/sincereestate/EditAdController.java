package com.g5.sincereestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditAdController implements Initializable {

    private int ownerID=DatabaseCenter.signedUserID;
    private int propertyID=DatabaseCenter.selectedProperty;
    byte[] imageData;
    @FXML
    private TextField buildingAgeField;

    @FXML
    private TextField cityField;

    @FXML
    private ChoiceBox<String> furnishedBox;

    @FXML
    private TextField priceField;

    @FXML
    private ImageView propertyImage;

    @FXML
    private ChoiceBox<Integer> roomsBox;

    @FXML
    private TextField squaremetersField;

    @FXML
    private ChoiceBox<String> statusBox;

    @FXML
    private TextField streetField;

    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    private TextField zipField;

    private boolean isImageChanged;

    @FXML
    void GoBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("propertypage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            SincereEstateApplication.stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeBox.setValue(DatabaseCenter.getPropertyData("property_type", propertyID));
        typeBox.getItems().addAll("house","townhome","multi-family","condos/co-ops","apartments","manufactured");
        statusBox.setValue(DatabaseCenter.getPropertyData("property_status", propertyID));
        statusBox.getItems().addAll("for sale", "for rent", "daily rent");
        furnishedBox.setValue(DatabaseCenter.getPropertyData("furnished", propertyID));
        furnishedBox.getItems().addAll("Yes","No");
        roomsBox.setValue(Integer.parseInt(DatabaseCenter.getPropertyData("number_of_rooms", propertyID)));
        roomsBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        Image image;
        if (DatabaseCenter.getImage(propertyID) == null) {
            image = new Image(HomePageController.class.getResourceAsStream("images/defimage.png"));
        } else {
            image = new Image(new ByteArrayInputStream(DatabaseCenter.getImage(propertyID)));
        }
        propertyImage.setImage(image);
        buildingAgeField.setText(DatabaseCenter.getPropertyData("building_age",propertyID));
        cityField.setText(DatabaseCenter.getPropertyData("city",propertyID));
        priceField.setText(DatabaseCenter.getPropertyData("property_price",propertyID));
        squaremetersField.setText(DatabaseCenter.getPropertyData("squaremeters",propertyID));
        streetField.setText(DatabaseCenter.getPropertyData("street",propertyID));
        zipField.setText(DatabaseCenter.getPropertyData("zip",propertyID));
    }

    @FXML
    public void addImage(){
        Stage stage = (Stage) propertyImage.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        propertyImage.setImage(new Image(selectedFile.toURI().toString()));
        try {
            imageData= Files.readAllBytes(selectedFile.toPath());
            isImageChanged=true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void updateAd(ActionEvent event){

        if(squaremetersField.getText().isEmpty() || buildingAgeField.getText().isEmpty() || cityField.getText().isEmpty()
                || streetField.getText().isEmpty() || zipField.getText().isEmpty() || priceField.getText().isEmpty()) {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Fill in the blanks!");
            alert.show();
            return;
        }
        if(!isImageChanged) {
            imageData=DatabaseCenter.getImage(propertyID);
        }

        if(DatabaseCenter.canUpdateProperty(typeBox.getValue(),statusBox.getValue(),furnishedBox.getValue(),
                roomsBox.getValue(),Integer.parseInt(squaremetersField.getText()),Integer.parseInt(buildingAgeField.getText()),cityField.getText(),streetField.getText(),
                Integer.parseInt(zipField.getText()),Integer.parseInt(priceField.getText()),imageData)) {
            GoPropertyScene(event);
        } else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Check your information!");
            alert.show();
        }
    }

    public void GoPropertyScene(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("propertypage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
