package com.codecool.shop.dao;

import com.codecool.shop.model.Location;

public interface LocationDao {
    void addNewLocation(Location location);
    Location find(int id);
}
