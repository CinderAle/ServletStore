package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class EditCartItemTask implements ITask {
    private static final Logger logger = Logger.getLogger(EditCartItemTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String product = request.getParameter("productId");
        String userId = request.getParameter("userId");
        String quantity = request.getParameter("quantity");
        try {
            productsDAO.editCartItem(Integer.parseInt(userId), Integer.parseInt(product), Integer.parseInt(quantity));
            return JspPages.CART_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to edit product in the cart!");
            logger.error(e.getMessage() + " for editing cart of " + userId);
            return JspPages.CART_PAGE;
        }
    }
}
