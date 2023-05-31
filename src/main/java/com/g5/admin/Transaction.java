package com.g5.admin;

import com.g5.sincereestate.DatabaseCenter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty propertyID;
    private SimpleStringProperty date;

    public Transaction(int id, int propertyID, String date) {
        this.id=new SimpleIntegerProperty(id);
        this.propertyID=new SimpleIntegerProperty(propertyID);
        this.date=new SimpleStringProperty(date);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getPropertyID() {
        return propertyID.get();
    }

    public SimpleIntegerProperty propertyIDProperty() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID.set(propertyID);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
