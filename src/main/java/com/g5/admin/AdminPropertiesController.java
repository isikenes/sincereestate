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

public class AdminPropertiesController implements Initializable {

    @FXML
    private TableView<Property> tableView;
    @FXML
    private TableColumn<Property, Integer> propertyID;
    @FXML
    private TableColumn<Property, Integer> ownerID;
    @FXML
    private TableColumn<Property, String> type;
    @FXML
    private TableColumn<Property, String> status;
    @FXML
    private TableColumn<Property, String> adDate;
    @FXML
    private TableColumn<Property, Integer> price;
    @FXML
    private TableColumn<Property, String> city;
    @FXML
    private TableColumn<Property, String> street;
    @FXML
    private TableColumn<Property, Integer> zip;
    @FXML
    private TableColumn<Property, String> furnished;
    @FXML
    private TableColumn<Property, Integer> rooms;
    @FXML
    private TableColumn<Property, Integer> age;
    @FXML
    private TableColumn<Property, Integer> squaremeters;



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
        propertyID.setCellValueFactory(new PropertyValueFactory<>("propertyID"));
        ownerID.setCellValueFactory(new PropertyValueFactory<>("ownerID"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        adDate.setCellValueFactory(new PropertyValueFactory<>("adDate"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        street.setCellValueFactory(new PropertyValueFactory<>("street"));
        zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        furnished.setCellValueFactory(new PropertyValueFactory<>("furnished"));
        rooms.setCellValueFactory(new PropertyValueFactory<>("rooms"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        squaremeters.setCellValueFactory(new PropertyValueFactory<>("squaremeters"));

        List<Property> propertyList = new ArrayList<>();

        try {
            String query = "SELECT * FROM properties";
            PreparedStatement statement = DatabaseCenter.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Property property=new Property(resultSet.getInt("property_id"), resultSet.getInt("owner_id"),resultSet.getString("property_type"), resultSet.getString("property_status"),resultSet.getString("ad_date"), resultSet.getInt("property_price"), resultSet.getString("city"), resultSet.getString("street"),resultSet.getInt("zip"), resultSet.getString("furnished"),resultSet.getInt("number_of_rooms"), resultSet.getInt("building_age"),resultSet.getInt("squaremeters"));

                propertyList.add(property);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableView.setItems(FXCollections.observableArrayList(propertyList));

    }
}
