package by.bsuir.servletstore.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StoreConnectionPool implements ConnectionPool {
    private static final String DATABASE_NAME = "database";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USER = "db.user";
    private static final String DATABASE_PASSWORD = "db.password";
    private static final int INITIAL_POOL_SIZE = 5;
    private final List<Connection> pool = new ArrayList<>();
    private final List<Connection> used = new ArrayList<>();

    private static Connection createConnection(String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    private void createPool(String url, String user, String password) throws SQLException, ClassNotFoundException {
        for(int i = 0;i < INITIAL_POOL_SIZE;i++) {
            pool.add(createConnection(url, user, password));
        }
    }

    private StoreConnectionPool() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_NAME);
        try {
            createPool(resourceBundle.getString(DATABASE_URL), resourceBundle.getString(DATABASE_USER), resourceBundle.getString(DATABASE_PASSWORD));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static StoreConnectionPool getInstance() {
        return new StoreConnectionPool();
    }

    @Override
    public Connection getConnection() {
        Connection connection = pool.remove(pool.size() - 1);
        used.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        pool.add(connection);
        return used.remove(connection);
    }
}
