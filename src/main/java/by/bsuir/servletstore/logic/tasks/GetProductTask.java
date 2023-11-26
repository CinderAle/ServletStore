package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.entities.Product;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class GetProductTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetProductTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String productId = request.getParameter("productId");
        try {
            Product product = productsDAO.getProduct(Integer.parseInt(productId));
            request.setAttribute("product", product);
            return JspPages.CART_PAGE;
        }
        catch (RuntimeException e) {
            request.setAttribute("error", "Failed to get the product!");
            logger.error(e.getMessage() + " for getting product " + productId);
            return JspPages.ERROR_PAGE;
        }
    }
}
