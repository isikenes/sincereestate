package com.g5.sincereestate;

import java.sql.*;

public class DatabaseCenter {

    static Connection connection;
    static int selectedProperty;
    static int signedUserID;
    static int scene;

    public static void ConnectToDB() {
        String host = "localhost";
        String port = "3306";
        String dbName = "realestatecompanydb";
        String user = "root";
        String password = "12345678";

        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Cannot connect to database!");
            e.printStackTrace();
        }
    }

    public static boolean isLoginInfoTrue(String email, String password) {
        String query = "SELECT user_email, user_password, user_id FROM users WHERE " +
                "user_email='" + email + "' AND " +
                "user_password='" + password + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                signedUserID=rs.getInt("user_id");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean canCreateFavorite(int userId,int propertyId) {
        String query = "INSERT INTO favorites VALUES(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, propertyId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean canCreateUser(String firstName, String lastName, String email, String password, String phone, String birthdate) {
        String query = "INSERT INTO users (first_name, last_name, user_email, user_password, phone_number, birth_date) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, phone);
            statement.setString(6, birthdate);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public static boolean canCreateProperty(String propertyType,String propertyStatus,String adDate,String furnished,
                                        int numberOfRooms, int squaremeters,int buildingAge,String city,
                                        String street, int zip,int price,byte[] imageData){
        String query = "INSERT INTO properties (owner_id, property_type, property_status, ad_date, property_price, city, street, zip, furnished, number_of_rooms, building_age, squaremeters, image) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, signedUserID);
            statement.setString(2, propertyType);
            statement.setString(3, propertyStatus);
            statement.setString(4, adDate);
            statement.setInt(5,price);
            statement.setString(6, city);
            statement.setString(7, street);
            statement.setInt(8,zip);
            statement.setString(9,furnished);
            statement.setInt(10,numberOfRooms);
            statement.setInt(11,buildingAge);
            statement.setInt(12,squaremeters);
            statement.setBytes(13,imageData);
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e){
            return false;
        }
    }

    public static boolean canUpdateProperty(String propertyType,String propertyStatus,String furnished,
                                            int numberOfRooms, int squaremeters,int buildingAge,String city,
                                            String street, int zip,int price,byte[] imageData){
        String query = "UPDATE properties SET property_type = ?, property_status=?, property_price=?, city=?, street=?, zip=?, furnished=?, number_of_rooms=?, building_age=?, squaremeters=?, image=? WHERE property_id="+selectedProperty;
        try{
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1, propertyType);
            statement.setString(2, propertyStatus);
            statement.setInt(3,price);
            statement.setString(4, city);
            statement.setString(5, street);
            statement.setInt(6,zip);
            statement.setString(7,furnished);
            statement.setInt(8,numberOfRooms);
            statement.setInt(9,buildingAge);
            statement.setInt(10,squaremeters);
            statement.setBytes(11,imageData);
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e){
            return false;
        }
    }

    public static void deleteFavorite(int userID,int propertyID){
        String query="DELETE FROM favorites WHERE user_id= ? AND property_id= ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,userID);
            statement.setInt(2,propertyID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteProperty(){
        String query="DELETE FROM properties WHERE property_id= ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,selectedProperty);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static byte[] getImage(int property_id) {
        String query = "SELECT image FROM properties WHERE property_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, property_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                byte[] imageData = rs.getBytes("image");

                rs.close();
                statement.close();
                return imageData;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setImage(int property_id, byte[] image_data) {
        String query = "UPDATE properties SET image = ? WHERE property_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setBytes(1, image_data);
            preparedStatement.setInt(2, property_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPropertyData(String columnName, int id) {
        String query = "SELECT " + columnName + " FROM properties WHERE property_id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString(columnName);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserData(String columnName, int id) {
        String query = "SELECT " + columnName + " FROM users WHERE user_id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString(columnName);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] getRandomProperties() {
        int[] indexes = new int[6];
        int a = 0;
        try {
            String sqlQuery = "SELECT property_id FROM properties ORDER BY RAND() LIMIT 6";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int propertyId = resultSet.getInt("property_id");
                indexes[a] = propertyId;
                a++;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indexes;
    }

    public static int[] getRandomPropertiesByFilter(String status) {
        int[] indexes = new int[6];
        int a = 0;
        try {
            String sqlQuery = "SELECT property_id FROM properties WHERE property_status = '" + status + "' ORDER BY RAND() LIMIT 6";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int propertyId = resultSet.getInt("property_id");
                indexes[a] = propertyId;
                a++;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indexes;
    }

    public static int[] getMyProperties() {
        int[] indexes = new int[6];
        int a = 0;
        try {
            String sqlQuery = "SELECT property_id FROM properties WHERE owner_id = "+signedUserID;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next() && a<=5) {
                int propertyId = resultSet.getInt("property_id");
                indexes[a] = propertyId;
                a++;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indexes;
    }

    public static int[] getMyFavorites() {
        int[] indexes = new int[6];
        int a = 0;
        try {
            String sqlQuery = "SELECT property_id FROM favorites WHERE user_id = " + signedUserID;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next() && a<=5) {
                int propertyId = resultSet.getInt("property_id");
                indexes[a] = propertyId;
                a++;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indexes;
    }

}

