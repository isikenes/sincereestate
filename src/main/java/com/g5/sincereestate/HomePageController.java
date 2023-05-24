package com.g5.sincereestate;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Ref;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Refresh();



    }

    public void Refresh() {
        int[] ints=new int[]{(int)(Math.random()*5000),(int)(Math.random()*5000),(int)(Math.random()*5000),(int)(Math.random()*5000),(int)(Math.random()*5000),(int)(Math.random()*5000)};
        ImageView[] imageViews= new ImageView[] {image1, image2, image3, image4, image5, image6};
        Label[] typeLabels=new Label[] {typeLabel1,typeLabel2, typeLabel3, typeLabel4, typeLabel5,typeLabel6};
        Label[] priceLabels=new Label[] {priceLabel1,priceLabel2,priceLabel3,priceLabel4,priceLabel5,priceLabel6};

        for(int i=0;i<ints.length;i++) {

            Image image;
            if(DatabaseCenter.getImage(ints[i])==null) {
                image=new Image(HomePageController.class.getResourceAsStream("defimage.png"));
            } else{
                image=new Image(new ByteArrayInputStream(DatabaseCenter.getImage(ints[i])));
            }
            image1.setImage(image);

            typeLabels[i].setText(DatabaseCenter.getPropertyType(ints[i]));
            priceLabels[i].setText(DatabaseCenter.getPropertyPrice(ints[i])+"$");
        }
    }
}
