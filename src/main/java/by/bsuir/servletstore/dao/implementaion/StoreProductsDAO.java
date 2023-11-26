package by.bsuir.servletstore.dao.implementaion;

import by.bsuir.servletstore.connection.ConnectionPool;
import by.bsuir.servletstore.connection.StoreConnectionPool;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.entities.Coupon;
import by.bsuir.servletstore.entities.Product;
import by.bsuir.servletstore.entities.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreProductsDAO implements ProductsDAO {
    private static final String PRODUCTS_ALREADY_EXISTS = "The product already exists!";
    private final ConnectionPool pool = StoreConnectionPool.getInstance();
    @Override
    public int addProduct(String name, String image, float price) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Products(name, image, price) values (?,?,?)", new String[]{"id"});
            statement.setString(1, name);
            statement.setString(2, image);
            statement.setFloat(3, price);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            int productId = 0;
            if(resultSet.next()) {
                productId = resultSet.getInt(1);
            }
            pool.releaseConnection(connection);
            return productId;
        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(PRODUCTS_ALREADY_EXISTS);
        }
    }

    @Override
    public void editProduct(int id, String name, String image, float price) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update Products set name = ?, image = ?, price = ? where id = ?");
            statement.setString(1, name);
            statement.setString(2, image);
            statement.setFloat(3, price);
            statement.setInt(4, id);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Products order by price desc");
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt("id");
                float price = result.getFloat("price");
                String name = result.getString("name");
                String image = result.getString("image");
                products.add(new Product(name, image, price, id));
            }
            pool.releaseConnection(connection);
        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void removeProduct(int id) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete * from Products where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Sales order by product asc");
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int productId = result.getInt("product");
                float sale = result.getFloat("sale");
                sales.add(new Sale(productId, sale));
            }
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
        return sales;
    }

    @Override
    public void addSale(int productId, float sale) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Sales(product, sale) values (?,?)");
            statement.setInt(1, productId);
            statement.setFloat(2, sale);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeSale(int productId) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete * from Sales where product = ?");
            statement.setInt(1, productId);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editSale(int productId, float newSale) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update Sales set sale = ? where product = ?");
            statement.setFloat(1, newSale);
            statement.setInt(2, productId);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Coupon> getAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Coupons order by name asc");
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                float sale = result.getFloat("sale");
                coupons.add(new Coupon(id, name, sale));
            }
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
        return coupons;
    }

    @Override
    public int addCoupon(String name, float sale) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Coupons(name, sale) values (?,?)", new String[]{"id"});
            statement.setString(1, name);
            statement.setFloat(2, sale);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            int couponId = 0;
            if (result.next()) {
                couponId = result.getInt(1);
            }
            pool.releaseConnection(connection);
            return couponId;
        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeCoupon(int id) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete * from Coupons where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editCoupon(int id, String name, float sale) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update Coupons set name = ?, sale = ? where id = ?");
            statement.setString(1, name);
            statement.setFloat(2, sale);
            statement.setInt(3, id);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Coupon getCouponById(int couponId) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Coupons where id = ?");
            statement.setInt(1, couponId);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                float sale = result.getFloat("sale");
                String name = result.getString("name");
                pool.releaseConnection(connection);
                return new Coupon(couponId, name, sale);
            }
            pool.releaseConnection(connection);
            throw new Exception("Coupon not found!");
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Sale getSale(int productId) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Sales where product = ?");
            statement.setInt(1, productId);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                float sale = result.getFloat("sale");
                pool.releaseConnection(connection);
                return new Sale(productId, sale);
            }
            pool.releaseConnection(connection);
            throw new Exception("Sale not found");
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Coupon getCoupon(String couponName) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Coupons where name = ?");
            statement.setString(1, couponName);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                float sale = result.getFloat("sale");
                int id = result.getInt("id");
                pool.releaseConnection(connection);
                return new Coupon(id, couponName, sale);
            }
            pool.releaseConnection(connection);
            throw new Exception("Coupon not found!");
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProduct(int productId) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Products where id = ?");
            statement.setInt(1, productId);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                String name = result.getString("name");
                String image = result.getString("picture");
                float price = result.getFloat("price");
                pool.releaseConnection(connection);
                return new Product(name, image, price, productId);
            }
            pool.releaseConnection(connection);
            throw new Exception("Product not found!");
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUserCartItem(int userId, int productId, int quantity) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Carts(user, product, quantity) values (?,?,?)");
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Integer, Integer> getUserCart(int userId) {
        Map<Integer, Integer> cart = new HashMap<>();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Carts where 'user' = ?");
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int productId = result.getInt("product");
                int quantity = result.getInt("quantity");
                cart.put(productId, quantity);
            }
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
        return cart;
    }

    @Override
    public void removeFromUserCart(int userId, int productId) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete * from Carts where ('user' = ? and product = ?)");
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearUserCart(int userId) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete * from Carts where 'user' = ?");
            statement.setInt(1, userId);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUserCoupon(int userId, int couponId) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into 'user coupons'('user', 'coupon') values (?,?)");
            statement.setInt(1, userId);
            statement.setInt(2, couponId);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getUserCouponId(int userId) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from 'user coupons' where 'user' = ?");
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                int couponId = result.getInt("coupon");
                pool.releaseConnection(connection);
                return couponId;
            }
            pool.releaseConnection(connection);
            throw new Exception("Coupon for the user not found!");
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserCoupon(int userId) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete * from 'user coupons' where 'user' = ?");
            statement.setInt(1, userId);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editCartItem(int userId, int productId, int quantity) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update Carts set quantity = ? where ('user' = ? and 'product' = ?)");
            statement.setInt(1, quantity);
            statement.setInt(2, userId);
            statement.setInt(3, productId);
            statement.executeUpdate();
            pool.releaseConnection(connection);
        }
        catch(Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }
}
