package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.util.Map;

public class GetUserCartTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetUserCartTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String userId = request.getParameter("userId");
        try {
            Map<Integer, Integer> cart = productsDAO.getUserCart(Integer.parseInt(userId));
            request.setAttribute("cart", cart);
            return JspPages.CART_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to get the cart!");
            logger.error(e.getMessage() + " for getting cart of " + userId);
            return JspPages.CART_PAGE;
        }
    }
}
