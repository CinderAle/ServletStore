package by.bsuir.servletstore.dao;

import by.bsuir.servletstore.entities.User;

public interface AuthDAO {
    User getUser(String email, String password);
    int registerUser(String name, String email, String password);
}
