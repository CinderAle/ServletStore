package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class RemoveSaleTask implements ITask {
    private static final Logger logger = Logger.getLogger(RemoveSaleTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String productId = request.getParameter("removeSaleProduct");
        try {
            productsDAO.removeSale(Integer.parseInt(productId));
            return JspPages.ADMIN_SALES_PAGE;
        }
        catch (RuntimeException e) {
            request.setAttribute("error", "Failed to remove the sale!");
            logger.error(e.getMessage() + " for removing sale on " + productId);
            return JspPages.ADMIN_SALES_PAGE;
        }
    }
}
