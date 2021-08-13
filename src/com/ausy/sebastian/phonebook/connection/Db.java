package com.ausy.sebastian.phonebook.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Db {
    private static Db instance = new Db();

    private Db() {
    }

    private static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        FileInputStream fis=new FileInputStream("connection.properties");
        Properties p=new Properties ();
        p.load (fis);
        String dName= (String) p.get ("dName");
        String url= (String) p.get ("url");
        String uName= (String) p.get ("uName");
        String password= (String) p.get ("password");

        Class.forName(dName);
        return DriverManager.getConnection(url, uName, password);
    }

    public static Db getInstance() {
        return instance;
    }
}
