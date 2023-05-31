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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FavoritesController implements Initializable {

    @FXML
    private Label hiLabel;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView image5;

    @FXML
    private ImageView image6;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private Pane pane3;

    @FXML
    private Pane pane4;

    @FXML
    private Pane pane5;

    @FXML
    private Pane pane6;

    @FXML
    private Label priceLabel1;

    @FXML
    private Label priceLabel2;

    @FXML
    private Label priceLabel3;

    @FXML
    private Label priceLabel4;

    @FXML
    private Label priceLabel5;

    @FXML
    private Label priceLabel6;

    @FXML
    private Label statusLabel1;

    @FXML
    private Label statusLabel2;

    @FXML
    private Label statusLabel3;

    @FXML
    private Label statusLabel4;

    @FXML
    private Label statusLabel5;

    @FXML
    private Label statusLabel6;

    @FXML
    private Label typeLabel1;

    @FXML
    private Label typeLabel2;

    @FXML
    private Label typeLabel3;

    @FXML
    private Label typeLabel4;

    @FXML
    private Label typeLabel5;

    @FXML
    private Label typeLabel6;

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

            SincereEstateApplication.stage.setScene(scene);

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
        int signedId=DatabaseCenter.signedUserID;
        indexes=DatabaseCenter.getMyFavorites();
        Pane[] panes=new Pane[] {pane1,pane2,pane3,pane4,pane5,pane6};


        ImageView[] imageViews= new ImageView[] {image1, image2, image3, image4, image5, image6};
        Label[] typeLabels=new Label[] {typeLabel1,typeLabel2, typeLabel3, typeLabel4, typeLabel5,typeLabel6};
        Label[] priceLabels=new Label[] {priceLabel1,priceLabel2,priceLabel3,priceLabel4,priceLabel5,priceLabel6};
        Label[] statusLabels=new Label[] {statusLabel1, statusLabel2, statusLabel3, statusLabel4, statusLabel5, statusLabel6};
        for (int i=0;i<indexes.length;i++){
            Image image;

            if(DatabaseCenter.getImage(indexes[i])==null && indexes[i]!=0) {
                String propertyType=DatabaseCenter.getPropertyData("property_type",indexes[i]);

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
                imageViews[i].setImage(image);
                //image=new Image(HomePageController.class.getResourceAsStream("images/defimage.png"));
            }
            else if(indexes[i]!=0){
                image=new Image(new ByteArrayInputStream(DatabaseCenter.getImage(indexes[i])));
                imageViews[i].setImage(image);
            }
            typeLabels[i].setText(DatabaseCenter.getPropertyData("property_type",indexes[i]));
            priceLabels[i].setText(DatabaseCenter.getPropertyData("property_price",indexes[i])+"$");
            statusLabels[i].setText(DatabaseCenter.getPropertyData("property_status",indexes[i]));
            if(DatabaseCenter.getPropertyData("property_type",indexes[i]) ==null) {
                panes[i].setVisible(false);
            }
        }
        hiLabel.setText("Hi, "+DatabaseCenter.getUserData("first_name",signedId)+" "+DatabaseCenter.getUserData("last_name",signedId)+"!");
        DatabaseCenter.scene=2;

    }
}

