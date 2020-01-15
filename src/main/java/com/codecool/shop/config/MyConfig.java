package com.codecool.shop.config;

import java.util.*;
import java.util.Properties;

public class MyConfig {
    Properties configFile;

    public MyConfig() {
        configFile = new Properties();

        try {
            configFile.load(this.getClass().getClassLoader().
                    getResourceAsStream("connection.properties.cfg"));
        } catch (Exception eta) {
            eta.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = this.configFile.getProperty(key);
        return value;
    }
}