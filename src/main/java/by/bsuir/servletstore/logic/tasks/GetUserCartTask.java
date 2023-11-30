package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.entities.Coupon;
import by.bsuir.servletstore.entities.Product;
import by.bsuir.servletstore.entities.Sale;
import by.bsuir.servletstore.entities.User;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetUserCartTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetUserCartTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            float totalSum = 0;
            Map<Integer, Integer> cart = productsDAO.getUserCart(user.getId());
            Map<Integer, Product> cartProducts = new HashMap<>();

            for (Integer productId : cart.keySet()) {
                Product currProduct = productsDAO.getProduct(productId);
                Sale sale = productsDAO.getSale(currProduct.getId());
                if(sale == null) {
                    totalSum += currProduct.getPrice() * cart.get(productId);
                    cartProducts.put(productId, currProduct);
                }
                else {

                    Product saleProduct = new Product(currProduct.getName(), currProduct.getImagePath(), currProduct.getPrice() * (100 - sale.getSale()) / 100, currProduct.getId());
                    totalSum += saleProduct.getPrice() * cart.get(productId);
                    cartProducts.put(productId, saleProduct);
                }
            }

            request.setAttribute("totalSum", totalSum);
            request.setAttribute("cartQuantities", cart);
            request.setAttribute("cartProducts", cartProducts);

            return new GetUserCouponTask().run(request);
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to get the cart!");
            if(user != null) {
                logger.error(e.getMessage() + " for getting cart of " + user.getId());
            }
            return JspPages.CART_PAGE;
        }
    }
}
