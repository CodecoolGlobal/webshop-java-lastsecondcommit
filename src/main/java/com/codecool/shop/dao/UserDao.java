package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {

    int addNewUser(User user);
    User find(int id);
}
