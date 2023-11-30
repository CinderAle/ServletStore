package by.bsuir.servletstore.dao;

import by.bsuir.servletstore.entities.User;

import java.util.List;

/*
 * Interface for accessing users data objects
 * @author CinderAle
 * @version 1.0
 * */
public interface UserControlDAO {
    /*
    * Method to get all users
    * @return list of user objects
    * */
    List<User> getAllUsers();
    /*
    * Method to set a user's information
    * @param id user's id
    * @param role new user role
    * @param banStatus new user status
    * */
    void setUserInfo(int id, int role, boolean banStatus);
}
