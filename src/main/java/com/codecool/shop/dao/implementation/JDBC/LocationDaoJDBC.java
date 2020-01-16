package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.LocationDao;
import com.codecool.shop.model.Location;
import com.codecool.shop.model.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDaoJDBC extends JDBC implements LocationDao {
    private static LocationDaoJDBC instance = null;

    private LocationDaoJDBC() {
    }

    public static LocationDaoJDBC getInstance() {
        if (instance == null) {
            instance = new LocationDaoJDBC();
        }
        return instance;
    }

    @Override
    public void addNewLocation(Location location) {
            String query =
                "INSERT INTO location (country, city, zip_code, address) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, location.getCountry());
            statement.setString(2, location.getCity());
            statement.setString(3, location.getZip());
            statement.setString(4, location.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    @Override
    public Location find(int id) {
        String query = "SELECT * FROM location WHERE id = ? ;";
        ResultSet resultSet = null;
        Location result = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new Location(
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("country"),
                        resultSet.getString("zip"));
                result.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
