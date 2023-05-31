package com.g5.admin;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Property {
    private SimpleIntegerProperty propertyID;
    private SimpleIntegerProperty ownerID;
    private SimpleStringProperty type;
    private SimpleStringProperty status;
    private SimpleStringProperty adDate;
    private SimpleIntegerProperty price;
    private SimpleStringProperty city;
    private SimpleStringProperty street;
    private SimpleIntegerProperty zip;
    private SimpleStringProperty furnished;
    private SimpleIntegerProperty rooms;
    private SimpleIntegerProperty age;
    private SimpleIntegerProperty squaremeters;

    public Property(int propertyID, int ownerID, String type, String status, String adDate, int price, String city, String street, int zip, String furnished, int rooms, int age, int squaremeters) {
        this.propertyID = new SimpleIntegerProperty(propertyID);
        this.ownerID = new SimpleIntegerProperty(ownerID);
        this.type = new SimpleStringProperty(type);
        this.status = new SimpleStringProperty(status);
        this.adDate = new SimpleStringProperty(adDate);
        this.price = new SimpleIntegerProperty(price);
        this.city = new SimpleStringProperty(city);
        this.street = new SimpleStringProperty(street);
        this.zip = new SimpleIntegerProperty(zip);
        this.furnished = new SimpleStringProperty(furnished);
        this.rooms = new SimpleIntegerProperty(rooms);
        this.age = new SimpleIntegerProperty(age);
        this.squaremeters = new SimpleIntegerProperty(squaremeters);
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

    public int getOwnerID() {
        return ownerID.get();
    }

    public SimpleIntegerProperty ownerIDProperty() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID.set(ownerID);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getAdDate() {
        return adDate.get();
    }

    public SimpleStringProperty adDateProperty() {
        return adDate;
    }

    public void setAdDate(String adDate) {
        this.adDate.set(adDate);
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public int getZip() {
        return zip.get();
    }

    public SimpleIntegerProperty zipProperty() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip.set(zip);
    }

    public String getFurnished() {
        return furnished.get();
    }

    public SimpleStringProperty furnishedProperty() {
        return furnished;
    }

    public void setFurnished(String furnished) {
        this.furnished.set(furnished);
    }

    public int getRooms() {
        return rooms.get();
    }

    public SimpleIntegerProperty roomsProperty() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms.set(rooms);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public int getSquaremeters() {
        return squaremeters.get();
    }

    public SimpleIntegerProperty squaremetersProperty() {
        return squaremeters;
    }

    public void setSquaremeters(int squaremeters) {
        this.squaremeters.set(squaremeters);
    }
}
