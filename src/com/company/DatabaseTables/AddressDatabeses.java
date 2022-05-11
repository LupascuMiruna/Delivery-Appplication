
package com.company.DatabaseTables;

import com.company.entities.Adress;
import com.company.entities.County;
import com.company.exceptions.CountyException;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.Arrays;

public class AddressDatabeses {
    private static AddressDatabeses instance;
    public static synchronized AddressDatabeses getInstance() {
        if (instance == null) {
            instance = new AddressDatabeses();
        }
        return instance;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS addresses" +
                "(id int PRIMARY KEY AUTO_INCREMENT, county varchar(30), city varchar(30), street varchar(30), number int)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAddress(String county, String city, String street, Integer number) {
        String insertAddressSql = "INSERT INTO addresses(county, city, street, number) VALUES(?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAddressSql);
            preparedStatement.setString(1, county);
            preparedStatement.setString(2, city);
            preparedStatement.setString(3, street);
            preparedStatement.setInt(4, number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Adress getAddressById(int id) {
        String selectSql = "SELECT * FROM addresses WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //if(resultSet.next())System.out.println(resultSet.getString(2));

            //return null;
            return mapToAddress(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateAddressStreet(String street, int id) {
        String updateNameSql = "UPDATE addresses SET street=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, street);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAddress(int id) {
        String updateNameSql = "DELETE from addresses WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllAddresses() {
        String updateNameSql = "DELETE from addresses";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Adress mapToAddress(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String countyStr = resultSet.getString(2);

            County county = null;
            try {
                county = Arrays.stream(County.values())
                        .filter(c -> c.getValue().equalsIgnoreCase(countyStr))
                        .findFirst()
                        .orElseThrow(() -> new CountyException("Wrong county")); ///it needs a lambda expression that returns an exception instance
            }
            catch (CountyException exception) {
                System.out.println(exception.getMessage());
            }
            return new Adress(county, resultSet.getString(3),resultSet.getString(4), resultSet.getInt(5));
        }

        return null;
    }
}
