package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class RemoveCartTask implements ITask {
    private static final Logger logger = Logger.getLogger(RemoveCartTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String userId = request.getParameter("userId");
        try {
            productsDAO.clearUserCart(Integer.parseInt(userId));
            return JspPages.CART_PAGE;
        }
        catch (RuntimeException e) {
            request.setAttribute("error", "Failed to remove the cart!");
            logger.error(e.getMessage() + " for removing cart of " + userId);
            return JspPages.CART_PAGE;
        }
    }
}
