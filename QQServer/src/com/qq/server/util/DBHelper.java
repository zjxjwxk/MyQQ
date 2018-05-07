package com.qq.server.util;

import java.sql.*;

public class DBHelper {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/qq_client";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "19981018";

    private static Connection conn = null;

    static {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //单例模式
    public static Connection getConnection() {
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = DBHelper.getConnection();
        String sql = "SELECT * FROM user";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("nickname") + " " + rs.getString("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}