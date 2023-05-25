package com.g5.sincereestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
    ChoiceBox<String> filterBox;

    private int[] indexes;
    private int mode=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Refresh();
        filterBox.setValue("Filter");
        filterBox.getItems().addAll("for sale", "for rent", "daily rent");
        filterBox.setOnAction(this::filterStatus);
    }

    public void Refresh() {

        if(mode==0) {
            indexes=new int[]{(int)(Math.random()*5000),(int)(Math.random()*5000),(int)(Math.random()*5000),(int)(Math.random()*5000),(int)(Math.random()*5000),(int)(Math.random()*5000)};
        } else if(mode==1) {
            indexes=DatabaseCenter.getRandomPropertiesForSale();
        } else if(mode==2) {
            indexes=DatabaseCenter.getRandomPropertiesForRent();
        } else if(mode==3) {
            indexes=DatabaseCenter.getRandomPropertiesDailyRent();
        }

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
            image1.setImage(image);

            typeLabels[i].setText(DatabaseCenter.getPropertyType(indexes[i]));
            priceLabels[i].setText(DatabaseCenter.getPropertyPrice(indexes[i])+"$");
            statusLabels[i].setText(DatabaseCenter.getPropertyStatus(indexes[i]));
        }
    }

    public void filterStatus(ActionEvent event) {
        if(filterBox.getValue().equals("for sale")) {
            mode=1;
            Refresh();
        } else if(filterBox.getValue().equals("for rent")) {
            mode=2;
            Refresh();
        }
        else if(filterBox.getValue().equals("daily rent")) {
            mode=3;
            Refresh();
        }
    }
}
