package com.qq.server.dao;

import com.qq.common.User;
import com.qq.server.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void insert(User user) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "INSERT user(nickname, number, password) VALUES(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getNickname());
        ps.setString(2, user.getNumber());
        ps.setString(3, user.getPassword());
        ps.execute();
    }

    public User get(String number) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "SELECT * FROM user " +
                "WHERE number=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, number);
        ResultSet rs = ps.executeQuery();

        User user = null;
        while (rs.next()){
            user = new User();
            user.setNickname(rs.getString("nickname"));
            user.setNumber(rs.getString("number"));
            user.setPassword(rs.getString("password"));
        }
        return user;
    }

    public List<User> query() throws Exception{
        Connection conn = DBHelper.getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM user");
        PreparedStatement ps = conn.prepareStatement(sb.toString());
        ResultSet rs = ps.executeQuery();
        List<User> users = new ArrayList<>();
        User user;
        while (rs.next()){
            user = new User();
            user.setNickname(rs.getString("nickname"));
            user.setNumber(rs.getString("number"));
            user.setPassword(rs.getString("password"));

            users.add(user);
        }
        return users;
    }
}
