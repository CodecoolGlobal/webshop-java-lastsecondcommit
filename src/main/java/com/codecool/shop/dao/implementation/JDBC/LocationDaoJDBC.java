package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.LocationDao;
import com.codecool.shop.model.Location;

import java.sql.PreparedStatement;
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

        insert into tablename (code) values ('1448523')
        WHERE not exists(select * from tablename where code='1448523')

            String query =
                "INSERT INTO location (country, city, zip_code, address) VALUES (?,?,?,?)"+
                "NOT EXIST (SELECT * FROM location" +
                "WHERE country = ? AND city = ? AND zip_code = ? AND address = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, location.ge);
            statement.setString(2, );
            statement.setString(3, );
            statement.setString(4, );
            statement.setString(5, );
            statement.setString(6, );
            statement.setString(7, );
            statement.setString(8, );
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Location getById(int id) {
        return null;
    }
}
