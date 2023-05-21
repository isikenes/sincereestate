package com.g5.sincereestate;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestImage extends Application {

    @Override
    public void start(Stage stage) {
        DatabaseCenter.ConnectToDB();
        VBox root = new VBox();

        ImageView imageView = new ImageView();
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);




        Button button = new Button("Select Image");
        button.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if(selectedFile!=null){
                try {
                    byte[] imageData=Files.readAllBytes(selectedFile.toPath());
                    DatabaseCenter.setImage(1,imageData);

                    Image image = new Image(new ByteArrayInputStream(DatabaseCenter.getImage(1)));
                    imageView.setImage(image);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        root.getChildren().addAll(imageView, button);
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
