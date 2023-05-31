package com.g5.admin;

import com.g5.sincereestate.DatabaseCenter;
import com.g5.sincereestate.SincereEstateApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminUsersController implements Initializable {

    @FXML
    TableView<User> userTable;
    @FXML
    TableColumn<User,Integer> userIdColumn;
    @FXML
    TableColumn<User,String> firstNameColumn;
    @FXML
    TableColumn<User,String> lastNameColumn;
    @FXML
    TableColumn<User,String> emailColumn;
    @FXML
    TableColumn<User,String> passwordColumn;
    @FXML
    TableColumn<User,String> phoneNumberColumn;
    @FXML
    TableColumn<User,String> birthDateColumn;
    @FXML
    TableColumn<User,Integer> ageColumn;

    @FXML
    public void GoBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SincereEstateApplication.class.getResource("admin-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            SincereEstateApplication.stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        List<User> users = new ArrayList<>();

        try {
            String query = "SELECT * FROM users";
            PreparedStatement statement = DatabaseCenter.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                User user = new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("user_email"),rs.getString("user_password"),rs.getString("phone_number"),rs.getString("birth_date"),rs.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userTable.setItems(FXCollections.observableArrayList(users));
    }

}
