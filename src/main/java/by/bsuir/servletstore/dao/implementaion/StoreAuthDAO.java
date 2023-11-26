package by.bsuir.servletstore.dao.implementaion;

import by.bsuir.servletstore.connection.StoreConnectionPool;
import by.bsuir.servletstore.dao.AuthDAO;
import by.bsuir.servletstore.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StoreAuthDAO implements AuthDAO {
    private static final String USER_NOT_FOUND = "The provided e-mail or password is incorrect!";
    private static final String USER_OCCUPIED = "The provided email is already occupied";
    private final StoreConnectionPool pool = StoreConnectionPool.getInstance();
    @Override
    public final User getUser(String email, String password) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Users where email = ?");
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                if(password.equals(result.getString("password"))) {
                    int id = result.getInt("id");
                    int role = result.getInt("role");
                    boolean bannedState = result.getBoolean("banned");
                    String name = result.getString("name");
                    pool.releaseConnection(connection);
                    return new User(id, name, email, role, bannedState);
                }
                else {
                    pool.releaseConnection(connection);
                    throw new Exception(USER_NOT_FOUND);
                }
            }
            else {
                pool.releaseConnection(connection);
                throw new Exception(USER_NOT_FOUND);
            }
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int registerUser(String name, String email, String password) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Users(name, email, password) values (?,?,?)", new String[]{"id"});
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            int userID = 0;
            if(result.next()) {
                userID = result.getInt(1);
            }
            pool.releaseConnection(connection);
            return userID;

        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(USER_OCCUPIED);
        }
    }
}
