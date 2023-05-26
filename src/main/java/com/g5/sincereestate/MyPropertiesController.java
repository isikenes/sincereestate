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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyPropertiesController implements Initializable {

    @FXML
    Pane pane1;
    @FXML
    Pane pane2;
    @FXML
    Pane pane3;
    @FXML
    Pane pane4;
    @FXML
    Pane pane5;
    @FXML
    Pane pane6;
    @FXML
    ImageView image1;
    @FXML
    ImageView image2;
    @FXML
    ImageView image3;
    @FXML
    ImageView image4;
    @FXML
    ImageView image5;
    @FXML
    ImageView image6;

    @FXML
    Label typeLabel1;
    @FXML
    Label typeLabel2;
    @FXML
    Label typeLabel3;
    @FXML
    Label typeLabel4;
    @FXML
    Label typeLabel5;
    @FXML
    Label typeLabel6;

    @FXML
    Label priceLabel1;
    @FXML
    Label priceLabel2;
    @FXML
    Label priceLabel3;
    @FXML
    Label priceLabel4;
    @FXML
    Label priceLabel5;
    @FXML
    Label priceLabel6;

    @FXML
    Label statusLabel1;

    @FXML
    Label statusLabel2;

    @FXML
    Label statusLabel3;

    @FXML
    Label statusLabel4;

    @FXML
    Label statusLabel5;

    @FXML
    Label statusLabel6;

    @FXML
    Label hiLabel;

    private int[] indexes;

    @FXML
    void GoHomePage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("homepage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoPropertyPage1(){
        DatabaseCenter.selectedProperty=indexes[0];
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("propertypage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);


            Stage stage = (Stage) pane1.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GoPropertyPage2(){
        DatabaseCenter.selectedProperty=indexes[1];
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("propertypage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);


            Stage stage = (Stage) pane2.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GoPropertyPage3(){
        DatabaseCenter.selectedProperty=indexes[2];
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("propertypage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);


            Stage stage = (Stage) pane3.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GoPropertyPage4(){
        DatabaseCenter.selectedProperty=indexes[3];
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("propertypage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);


            Stage stage = (Stage) pane4.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GoPropertyPage5(){
        DatabaseCenter.selectedProperty=indexes[4];
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("propertypage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);


            Stage stage = (Stage) pane5.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GoPropertyPage6(){
        DatabaseCenter.selectedProperty=indexes[5];
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("propertypage-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) pane6.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int ownerID=DatabaseCenter.signedUserID;
        indexes=DatabaseCenter.getMyProperties();
        Pane[] panes=new Pane[] {pane1,pane2,pane3,pane4,pane5,pane6};


        ImageView[] imageViews= new ImageView[] {image1, image2, image3, image4, image5, image6};
        Label[] typeLabels=new Label[] {typeLabel1,typeLabel2, typeLabel3, typeLabel4, typeLabel5,typeLabel6};
        Label[] priceLabels=new Label[] {priceLabel1,priceLabel2,priceLabel3,priceLabel4,priceLabel5,priceLabel6};
        Label[] statusLabels=new Label[] {statusLabel1, statusLabel2, statusLabel3, statusLabel4, statusLabel5, statusLabel6};

        for(int i=0;i<indexes.length;i++) {
            Image image;
            if(DatabaseCenter.getImage(indexes[i])==null) {
                image=new Image(HomePageController.class.getResourceAsStream("images/defimage.png"));
            } else{
                image=new Image(new ByteArrayInputStream(DatabaseCenter.getImage(indexes[i])));
            }

            imageViews[i].setImage(image);
            typeLabels[i].setText(DatabaseCenter.getPropertyData("property_type",indexes[i]));
            priceLabels[i].setText(DatabaseCenter.getPropertyData("property_price",indexes[i])+"$");
            statusLabels[i].setText(DatabaseCenter.getPropertyData("property_status",indexes[i]));
            if(DatabaseCenter.getPropertyData("property_type",indexes[i]) ==null) {
                panes[i].setVisible(false);
            }
        }
        hiLabel.setText("Hi, "+DatabaseCenter.getUserData("first_name",ownerID)+" "+DatabaseCenter.getUserData("last_name",ownerID)+"!");
        DatabaseCenter.switcher=false;
    }
}