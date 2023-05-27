package com.g5.sincereestate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SincereEstateApplication extends Application {

    public static Stage stage;
    @Override
    public void start(Stage pstage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("main-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage=new Stage();
        stage.setTitle("Sincere Estate");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/logo.png")));
        stage.setResizable(false);
        stage.show();
        DatabaseCenter.ConnectToDB();
    }

    public static void main(String[] args) {
        launch();
    }
}