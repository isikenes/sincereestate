package com.g5.sincereestate;

import java.sql.*;

public class DatabaseCenter {

    static Connection connection;
    static int selectedProperty;

    public static void ConnectToDB() {
        String host = "localhost";
        String port = "3306";
        String dbName = "realestatecompanydb";
        String user = "root";
        String password = "12345678";

        String url="jdbc:mysql://"+ host +":"+ port +"/"+ dbName;

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
        String query="SELECT user_email, user_password FROM users WHERE " +
                "user_email='"+email+"' AND " +
                "user_password='"+password+"'";
        try {
            Statement statement=connection.createStatement();
            ResultSet rs= statement.executeQuery(query);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean canCreateUser(String firstName, String lastName, String email, String password, String phone, String birthdate) {
        String query="INSERT INTO users (first_name, last_name, user_email, user_password, phone_number, birth_date) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,email);
            statement.setString(4,password);
            statement.setString(5,phone);
            statement.setString(6,birthdate);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static byte[] getImage(int property_id){
        String query="SELECT image FROM properties WHERE property_id = ?";
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

    public static void setImage(int property_id, byte[] image_data){
        String query="UPDATE properties SET image = ? WHERE property_id = ?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(query);

            preparedStatement.setBytes(1,image_data);
            preparedStatement.setInt(2,property_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPropertyType(int id) {
        String query="SELECT property_type FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("property_type");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPropertyPrice(int id) {
        String query="SELECT property_price FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("property_price");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPropertyStatus(int id) {
        String query="SELECT property_status FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("property_status");
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getAdDate(int id) {
        String query="SELECT ad_date FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("ad_date");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getCity(int id) {
        String query="SELECT city FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("city");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getStreet(int id) {
        String query="SELECT street FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("street");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getZip(int id) {
        String query="SELECT zip FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("zip");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getFurnished(int id) {
        String query="SELECT furnished FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("furnished");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getNumberOfRooms(int id) {
        String query="SELECT number_of_rooms FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("number_of_rooms");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getBuildingAge(int id) {
        String query="SELECT building_age FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("building_age");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getSquareMeters(int id) {
        String query="SELECT squaremeters FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("squaremeters");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getOwnerID(int id) {
        String query="SELECT owner_id FROM properties WHERE property_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getInt("owner_id");
            }
            else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getOwnerFullName(int id) {
        String query="SELECT first_name,last_name FROM users WHERE user_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("first_name")+" "+rs.getString("last_name");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getOwnerPhoneNumber(int id) {
        String query="SELECT phone_number FROM users WHERE user_id = "+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("phone_number");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static int[] getRandomPropertiesForSale() {
        int[] indexes=new int[6];
        int a=0;
        try {
            String sqlQuery = "SELECT property_id FROM properties WHERE property_status = 'for sale' ORDER BY RAND() LIMIT 6";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int propertyId = resultSet.getInt("property_id");
                indexes[a]=propertyId;
                a++;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indexes;
    }

    public static int[] getRandomPropertiesForRent() {
        int[] indexes=new int[6];
        int a=0;
        try {
            String sqlQuery = "SELECT property_id FROM properties WHERE property_status = 'for rent' ORDER BY RAND() LIMIT 6";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int propertyId = resultSet.getInt("property_id");
                indexes[a]=propertyId;
                a++;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indexes;
    }

    public static int[] getRandomPropertiesDailyRent() {
        int[] indexes=new int[6];
        int a=0;
        try {
            String sqlQuery = "SELECT property_id FROM properties WHERE property_status = 'daily rent' ORDER BY RAND() LIMIT 6";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int propertyId = resultSet.getInt("property_id");
                indexes[a]=propertyId;
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
