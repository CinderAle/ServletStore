package by.bsuir.servletstore.dao;

import by.bsuir.servletstore.entities.User;

/*
* Interface for accessing auth data objects
* @author CinderAle
* @version 1.0
* */
public interface AuthDAO {
    /*
    Method to get a user
    @param email user's email
    @param password user's password
    @return user object of User
    * */
    User getUser(String email, String password);
    /*
    * Method to register a user
    * @param name user's name
    * @param email user's email
    * @param password hash of user's password
    * @return new user's userId
    * */
    int registerUser(String name, String email, String password);
}
