package by.bsuir.servletstore.dao;

import by.bsuir.servletstore.entities.User;

import java.util.List;

public interface UserControlDAO {
    List<User> getAllUsers();
    void setUserInfo(int id, int role, boolean banStatus);
}
