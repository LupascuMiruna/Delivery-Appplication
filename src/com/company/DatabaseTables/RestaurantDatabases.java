package com.company.DatabaseTables;

import com.company.entities.*;
import com.company.exceptions.CountyException;
import com.company.services.ReadService;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantDatabases {
    private static RestaurantDatabases instance;
    public static synchronized RestaurantDatabases getInstance() {
        if (instance == null) {
            instance = new RestaurantDatabases();
        }
        return instance;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS restaurants" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30), addressId int, FOREIGN KEY (addressId) REFERENCES addresses(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRestaurant(String name,Integer addressId) {
        String insertAddressSql = "INSERT INTO restaurants(name,addressId) VALUES(?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAddressSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, addressId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Restaurant getRestaurantById(int id, AddressDatabeses addressDatabeses) {
        String selectSql = "SELECT * FROM restaurants WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            return mapToRestaurant(resultSet, addressDatabeses);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateRestaurantName(String name, int id) {
        String updateNameSql = "UPDATE restaurants SET name=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRestaurant(int id) {
        String updateNameSql = "DELETE from restaurants WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllRestaurants() {
        String updateNameSql = "DELETE from restaurants";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Restaurant> getAllRestaurants( AddressDatabeses addressDatabeses) {
        String updateNameSql = "SELECT *  from restaurants";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            return mapAllRestaurants(resultSet, addressDatabeses);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Restaurant> mapAllRestaurants(ResultSet resultSet, AddressDatabeses addressDatabeses) throws SQLException {
        List<Restaurant> restaurants = new ArrayList<>();
        while (resultSet.next()) {
            Adress address = null;
            address = addressDatabeses.getAddressById(resultSet.getInt(3));
            Restaurant currenRestaurant = new Restaurant(resultSet.getString(2), address);

            /***
             * USE THE METHOD FROM THE 2ND PART
             * PRODUCTS READ FROM FILE
             */
            ReadService readService = ReadService.getInstance();
            List<Product> products = readService.readProducts(resultSet.getString(1));
            currenRestaurant.addProducts(products);
            restaurants.add(currenRestaurant);
        }
        return restaurants;
    }

    private Restaurant mapToRestaurant(ResultSet resultSet, AddressDatabeses addressDatabeses) throws SQLException {
        if (resultSet.next()) {
            Adress address = null;
            address = addressDatabeses.getAddressById(resultSet.getInt(3));
            return new Restaurant(resultSet.getString(2), address);
        }

        return null;
    }
}
