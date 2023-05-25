package com.g5.sincereestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PropertyController implements Initializable {
    private  int selectedProperty=DatabaseCenter.selectedProperty;
    @FXML
    private Label adDateLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label buildingAgeLabel;

    @FXML
    private Label furnishedLabel;

    @FXML
    private Label ownerPhoneNumberLabel;

    @FXML
    private Label ownernameLabel;

    @FXML
    private ImageView propertyImage;

    @FXML
    private Label propertyPriceLabel;

    @FXML
    private Label roomLabel;

    @FXML
    private Label squarametersLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label typeLabel;

    @FXML
    void GoHomePage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("homepage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int owner_id= DatabaseCenter.getOwnerID(selectedProperty);
        adDateLabel.setText("Ad Date: " + DatabaseCenter.getAdDate(selectedProperty));

        addressLabel.setText("Address:"+DatabaseCenter.getCity(selectedProperty)
                +" "+DatabaseCenter.getStreet(selectedProperty)+" "
                + DatabaseCenter.getZip(selectedProperty));

        buildingAgeLabel.setText("Building Age: "+DatabaseCenter.getBuildingAge(selectedProperty));
        furnishedLabel.setText("Furnished: "+DatabaseCenter.getFurnished(selectedProperty));

        Image image;
        if(DatabaseCenter.getImage(selectedProperty)==null) {
            image=new Image(HomePageController.class.getResourceAsStream("images/defimage.png"));
        } else{
            image=new Image(new ByteArrayInputStream(DatabaseCenter.getImage(selectedProperty)));
        }
        propertyImage.setImage(image);
        ownernameLabel.setText(DatabaseCenter.getOwnerFullName(owner_id));
        ownerPhoneNumberLabel.setText(DatabaseCenter.getOwnerPhoneNumber(owner_id));

        propertyPriceLabel.setText(DatabaseCenter.getPropertyPrice(selectedProperty)+"$");
        roomLabel.setText("Number Of Rooms: "+DatabaseCenter.getNumberOfRooms(selectedProperty));
        squarametersLabel.setText("m²: "+DatabaseCenter.getSquareMeters(selectedProperty));
        statusLabel.setText("Status: "+DatabaseCenter.getPropertyStatus(selectedProperty));
        typeLabel.setText("Type: "+DatabaseCenter.getPropertyType(selectedProperty));
    }
}