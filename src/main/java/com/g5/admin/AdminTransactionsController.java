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

public class AdminTransactionsController implements Initializable {
    @FXML
    private TableView<Transaction> tableView;
    @FXML
    private TableColumn<Property, Integer> transactionID;
    @FXML
    private TableColumn<Property, Integer> propertyID;
    @FXML
    private TableColumn<Property, String> date;

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
        transactionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        propertyID.setCellValueFactory(new PropertyValueFactory<>("propertyID"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));


        List<Transaction> transactionList = new ArrayList<>();

        try {
            String query = "SELECT * FROM transactions";
            PreparedStatement statement = DatabaseCenter.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Transaction transaction=new Transaction(resultSet.getInt("transaction_id"),resultSet.getInt("property_id"),resultSet.getString("transaction_date"));

                transactionList.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableView.setItems(FXCollections.observableArrayList(transactionList));
    }
}
