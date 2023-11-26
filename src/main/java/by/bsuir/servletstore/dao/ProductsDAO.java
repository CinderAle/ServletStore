package by.bsuir.servletstore.dao;

import by.bsuir.servletstore.entities.Coupon;
import by.bsuir.servletstore.entities.Product;
import by.bsuir.servletstore.entities.Sale;

import java.util.List;
import java.util.Map;

public interface ProductsDAO {
    int addProduct(String name, String image, float price);
    void editProduct(int id, String name, String image, float price);
    List<Product> getAllProducts();
    void removeProduct(int id);
    List<Sale> getAllSales();
    Sale getSale(int productId);
    void addSale(int productId, float sale);
    void removeSale(int productId);
    void editSale(int productId, float newSale);
    List<Coupon> getAllCoupons();
    Coupon getCoupon(String couponName);
    Coupon getCouponById(int couponId);
    int addCoupon(String name, float sale);
    void removeCoupon(int id);
    void editCoupon(int id, String name, float sale);
    Product getProduct(int productId);
    void addUserCartItem(int userId, int productId, int quantity);
    Map<Integer, Integer> getUserCart(int userId);
    void removeFromUserCart(int userId, int productId);
    void clearUserCart(int userId);
    void addUserCoupon(int userId, int couponId);
    int getUserCouponId(int userId);
    void removeUserCoupon(int userId);
    void editCartItem(int userId, int productId, int quantity);
}
