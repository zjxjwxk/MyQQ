package com.qq.server.action;

import com.qq.server.dao.UserDAO;
import com.qq.common.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAction {

    public boolean ifExists(String number){
        UserDAO userDAO = new UserDAO();
        List<User> users = new ArrayList<>();
        try {
            users = userDAO.query();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User user :
                users) {
            if (user.getNumber().equals(number)){
                return true;
            }
        }
        return false;
    }

    public boolean ifCorrect(String number, String password){
        UserDAO userDAO = new UserDAO();
        List<User> users = new ArrayList<>();
        try {
            users = userDAO.query();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User user:
                users) {
            if (user.getNumber().equals(number)){
                if (user.getPassword().equals(password))
                    return true;
            }
        }
        return false;
    }

    public void add(User user){
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User get(String number){
        UserDAO userDAO = new UserDAO();
        User user = null;
        try {
            user = userDAO.get(number);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers(){
        UserDAO userDAO = new UserDAO();
        try {
            return userDAO.query();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
