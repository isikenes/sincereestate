package com.g5.sincereestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PropertyController implements Initializable {

    private int selectedProperty = DatabaseCenter.selectedProperty;

    @FXML
    private Button favoriteButton;
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
    void addFavorites(ActionEvent event) {
        if (DatabaseCenter.scene == 0) {

            if (DatabaseCenter.canCreateFavorite(DatabaseCenter.signedUserID, selectedProperty)) {
                favoriteButton.setText("Added to Favorites");
                favoriteButton.setDisable(true);
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
            favoriteButton.setText("Add to Favorites");

        } else if (DatabaseCenter.scene == 1) {
            favoriteButton.setText("Delete Property");

        } else if (DatabaseCenter.scene == 2) {
            favoriteButton.setText("Remove from favorites");

        }
        adDateLabel.setText("Ad Date: " + DatabaseCenter.getPropertyData("ad_date", selectedProperty));

        addressLabel.setText("Address: " + DatabaseCenter.getPropertyData("street", selectedProperty) + " street, "
                + DatabaseCenter.getPropertyData("city", selectedProperty) + ", "
                + DatabaseCenter.getPropertyData("zip", selectedProperty));

        buildingAgeLabel.setText("Building Age: " + DatabaseCenter.getPropertyData("building_age", selectedProperty));
        furnishedLabel.setText("Furnished: " + DatabaseCenter.getPropertyData("furnished", selectedProperty));
        Image image;
        if (DatabaseCenter.getImage(selectedProperty) == null) {
            image = new Image(HomePageController.class.getResourceAsStream("images/defimage.png"));
        } else {
            image = new Image(new ByteArrayInputStream(DatabaseCenter.getImage(selectedProperty)));
        }
        propertyImage.setImage(image);

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