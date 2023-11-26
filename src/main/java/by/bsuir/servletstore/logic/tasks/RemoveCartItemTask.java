package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.net.Inet4Address;

public class RemoveCartItemTask implements ITask {
    private static final Logger logger = Logger.getLogger(RemoveCartItemTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String userId = request.getParameter("userId");
        String productId = request.getParameter("productId");
        try {
            productsDAO.removeFromUserCart(Integer.parseInt(userId), Integer.parseInt(productId));
            return JspPages.CART_PAGE;
        }
        catch (RuntimeException e) {
            request.setAttribute("error", "Failed to remove the cart!");
            logger.error(e.getMessage() + "for removing item from cart of" + userId);
            return JspPages.ERROR_PAGE;
        }
    }
}
