package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.entities.Sale;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class GetSaleTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetSalesTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String productId = request.getParameter("productId");
        try {
            Sale sale = productsDAO.getSale(Integer.parseInt(productId));
            request.setAttribute("sale", sale);
            return JspPages.CART_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to get the sale!");
            logger.error(e.getMessage() + " for getting sale on");
            return JspPages.ERROR_PAGE;
        }
    }
}
