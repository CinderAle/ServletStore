package by.bsuir.servletstore.dao.implementaion;

import by.bsuir.servletstore.connection.ConnectionPool;
import by.bsuir.servletstore.connection.StoreConnectionPool;
import by.bsuir.servletstore.dao.UserControlDAO;
import by.bsuir.servletstore.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StoreUserControlDAO implements UserControlDAO {
    private final ConnectionPool pool = StoreConnectionPool.getInstance();
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Users order by banned asc");
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                int role = result.getInt("role");
                boolean bannedState = result.getBoolean("banned");
                users.add(new User(id, name, email, role, bannedState));
            }
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void setUserInfo(int id, int role, boolean banStatus) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update Users set role = ?, banned = ? where id = ?");
            statement.setInt(1, role);
            statement.setBoolean(2, banStatus);
            statement.setInt(3, id);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }
}
