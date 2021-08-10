package com.ausy.sebastian;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public Connection get_Connection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "root");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
