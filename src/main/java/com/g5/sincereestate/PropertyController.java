package com.g5.sincereestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PropertyController implements Initializable {

    private int selectedProperty = DatabaseCenter.selectedProperty;

    @FXML
    private Button multipleJobButton;
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
    private Button editButton;

    @FXML
    void GoBack(ActionEvent event) {
        try {
            String name = "";
            switch (DatabaseCenter.scene) {
                case 0:
                    name = "homepage-scene.fxml";
                    break;
                case 1:
                    name = "my-properties-scene.fxml";
                    break;
                case 2:
                    name = "favorites-scene.fxml";
                    break;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource(name));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            SincereEstateApplication.stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GoEditPage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("editad-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            SincereEstateApplication.stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void doMultipleThing(ActionEvent event) {
        if (DatabaseCenter.scene == 0) {
            if (DatabaseCenter.canCreateFavorite(DatabaseCenter.signedUserID, selectedProperty)) {
                multipleJobButton.setText("Added to Favorites");
                multipleJobButton.setDisable(true);
                return;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This is your property!");
                alert.show();
            }
        } else if (DatabaseCenter.scene == 1) {
            DatabaseCenter.deleteProperty();
            GoBack(event);
        } else if (DatabaseCenter.scene == 2) {
            DatabaseCenter.deleteFavorite(DatabaseCenter.signedUserID, selectedProperty);
            GoBack(event);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (DatabaseCenter.scene == 0) {
            multipleJobButton.setText("Add to Favorites");
            editButton.setVisible(false);
        } else if (DatabaseCenter.scene == 1) {
            multipleJobButton.setText("Delete Property");

        } else if (DatabaseCenter.scene == 2) {
            multipleJobButton.setText("Remove from favorites");
            editButton.setVisible(false);
        }
        adDateLabel.setText("Ad Date: " + DatabaseCenter.getPropertyData("ad_date", selectedProperty));

        addressLabel.setText("Address: " + DatabaseCenter.getPropertyData("street", selectedProperty) + " street, "
                + DatabaseCenter.getPropertyData("city", selectedProperty) + ", "
                + DatabaseCenter.getPropertyData("zip", selectedProperty));

        buildingAgeLabel.setText("Building Age: " + DatabaseCenter.getPropertyData("building_age", selectedProperty));
        furnishedLabel.setText("Furnished: " + DatabaseCenter.getPropertyData("furnished", selectedProperty));
        Image image;
        if(DatabaseCenter.getImage(selectedProperty)==null) {
            String propertyType=DatabaseCenter.getPropertyData("property_type",selectedProperty);

            String imagePath="";
            switch (propertyType){
                case "house":
                    imagePath="images/house.jpg"; break;
                case "manufactured":
                    imagePath="images/manufactured.jpg"; break;
                case "multi-family":
                    imagePath="images/multi-family.jpg"; break;
                case "townhome":
                    imagePath="images/townhome.jpg"; break;
                case "condos/co-ops":
                    imagePath="images/condos.png"; break;
                case "apartments":
                    imagePath="images/apartment.png"; break;
                default:imagePath="images/defimage.png";
            }
            image=new Image(HomePageController.class.getResourceAsStream(imagePath));
            propertyImage.setImage(image);
            //image=new Image(HomePageController.class.getResourceAsStream("images/defimage.png"));
        }
        else{
            image=new Image(new ByteArrayInputStream(DatabaseCenter.getImage(selectedProperty)));
            propertyImage.setImage(image);
        }

        int owner_id = Integer.parseInt(DatabaseCenter.getPropertyData("owner_id", selectedProperty));
        ownernameLabel.setText(DatabaseCenter.getUserData("first_name", owner_id) + " " + DatabaseCenter.getUserData("last_name", owner_id));
        ownerPhoneNumberLabel.setText("+" + DatabaseCenter.getUserData("phone_number", owner_id));

        propertyPriceLabel.setText(DatabaseCenter.getPropertyData("property_price", selectedProperty) + "$");
        roomLabel.setText("Number Of Rooms: " + DatabaseCenter.getPropertyData("number_of_rooms", selectedProperty));
        squarametersLabel.setText("m²: " + DatabaseCenter.getPropertyData("squaremeters", selectedProperty));
        statusLabel.setText("Status: " + DatabaseCenter.getPropertyData("property_status", selectedProperty));
        typeLabel.setText("Type: " + DatabaseCenter.getPropertyData("property_type", selectedProperty));
    }
}