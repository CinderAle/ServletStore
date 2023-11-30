package by.bsuir.servletstore.connection;

import java.sql.Connection;

/*
* Interface of Connection pool
* @author CinderAle
* @version 1.0
* */
public interface ConnectionPool {
    /*
    * Method to get connection object
    * @return connection object
    * */
    Connection getConnection();

    /*
    * Method to release connection
    * @param connection Connection to be released
    * @return result of releasing the connection
    * */
    boolean releaseConnection(Connection connection);
}
