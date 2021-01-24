package com.ljw.spring.source.s1.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public final static String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/testdb?useUnicode=true&characterEncoding=UTF-8";
    public final static String DB_USERNAME = "root";
    public final static String DB_PASSWORD = "root";

    public static Connection getConnection() throws ClassNotFoundException,
            SQLException {
        Connection con = null;
        Class.forName(DB_DRIVER_CLASS);
        con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        System.out.println("DB Connection created successfully");
        return con;
    }

    public static void main(String[] args) {
        System.out.println("李金悟 = " + args);


    }
}
