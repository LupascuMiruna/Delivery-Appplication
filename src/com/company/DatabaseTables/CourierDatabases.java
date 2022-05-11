package com.company.DatabaseTables;

import com.company.entities.Adress;
import com.company.entities.County;
import com.company.entities.Courier;
import com.company.exceptions.CountyException;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.Arrays;

public class CourierDatabases {
    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS couriers" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30), transport varchar(30), rating double, numberRatings int, score double )";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCourier(String name, String transport, Double rating, Integer numberRatings, Double score) {
        String insertAddressSql = "INSERT INTO couriers(name, transport, rating, numberRatings, score) VALUES(?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAddressSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, transport);
            preparedStatement.setDouble(3, rating);
            preparedStatement.setInt(4, numberRatings);
            preparedStatement.setDouble(5, score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Courier getCourierById(int id) {
        String selectSql = "SELECT * FROM couriers WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //if(resultSet.next())System.out.println(resultSet.getString(2));

            //return null;
            return mapToCourier(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateCourierTransport(String transport, int id) {
        String updateNameSql = "UPDATE couriers SET transport=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, transport);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourier(int id) {
        String updateNameSql = "DELETE from couriers WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCouriers() {
        String updateNameSql = "DELETE from couriers";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Courier mapToCourier(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {

            return new Courier(resultSet.getString(2), resultSet.getString(3),resultSet.getDouble(4), resultSet.getInt(5),resultSet.getDouble(6));
        }

        return null;
    }


}
