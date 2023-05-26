package com.g5.sincereestate;

import java.sql.*;

public class DatabaseCenter {

    static Connection connection;
    static int selectedProperty;
    static int signedUserID;
    static boolean switcher;

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
    public static boolean canCreateProperty(int ownerId,String propertyType,String propertyStatus,String adDate,String furnished,
                                        int numberOfRooms, int squaremeters,int buildingAge,String city,
                                        String street, int zip,int price,byte[] imageData){
        String query = "INSERT INTO properties (owner_id, property_type, property_status, ad_date, property_price, city, street, zip, furnished, number_of_rooms, building_age, squaremeters, image) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, ownerId);
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
            return true;
        }
        catch (SQLException e){
            return false;
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
}
