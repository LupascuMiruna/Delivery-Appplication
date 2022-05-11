package com.company.DatabaseTables;

import com.company.entities.*;
import com.company.exceptions.CountyException;
import com.company.services.ReadService;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDatabases {
    private static UserDatabases instance;
    public static synchronized UserDatabases getInstance() {
        if (instance == null) {
            instance = new UserDatabases();
        }
        return instance;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS users" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30), addressId int , password varchar(30), email varchar(30), FOREIGN KEY (addressId) REFERENCES addresses(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(String name, Integer addressId, String password, String email) {
        String insertAddressSql = "INSERT INTO users(name, addressId, password, email) VALUES(?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAddressSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, addressId);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id, AddressDatabeses addressDatabeses) {
        String selectSql = "SELECT * FROM users WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToUser(resultSet, addressDatabeses);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateUserEmail(String email, int id) {
        String updateNameSql = "UPDATE users SET email=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String updateNameSql = "DELETE from users WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllUsers() {
        String updateNameSql = "DELETE from users";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(AddressDatabeses addressDatabeses) {
        String updateNameSql = "SELECT *  from users";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            return mapAllUsers(resultSet, addressDatabeses);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> mapAllUsers(ResultSet resultSet, AddressDatabeses addressDatabeses) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            Adress address = null;
            address = addressDatabeses.getAddressById(resultSet.getInt(3));
            User currenUser = new User(resultSet.getString(2), address, resultSet.getString(4), resultSet.getString(5));
            users.add(currenUser);
        }
        return users;
    }

    private User mapToUser(ResultSet resultSet, AddressDatabeses addressDatabeses) throws SQLException {
        if (resultSet.next()) {
            Adress address = null;
            address = addressDatabeses.getAddressById(resultSet.getInt(3));
            return new User(resultSet.getString(2), address, resultSet.getString(4), resultSet.getString(5));
        }

        return null;
    }
}
