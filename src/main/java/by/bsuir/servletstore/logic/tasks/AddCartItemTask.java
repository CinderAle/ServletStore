package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class AddCartItemTask implements ITask {
    private static final Logger logger = Logger.getLogger(AddCartItemTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String userId = request.getParameter("userId");
        String productId = request.getParameter("productId");
        String quantity = request.getParameter("quantity");
        try {
            productsDAO.addUserCartItem(Integer.parseInt(userId), Integer.parseInt(productId), Integer.parseInt(quantity));
            return new GetUserCartTask().run(request);
        }
        catch (RuntimeException e) {
            request.setAttribute("error", "Failed to add cart item!");
            logger.error(e.getMessage() + " for new cart item " + productId);
            return JspPages.ERROR_PAGE;
        }
    }
}
