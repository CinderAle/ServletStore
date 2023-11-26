package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.entities.Sale;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.util.List;

public class GetSalesTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetSalesTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        try {
            List<Sale> sales = productsDAO.getAllSales();
            request.setAttribute("sales", sales);
            return JspPages.ADMIN_SALES_PAGE;
        }
        catch (RuntimeException e) {
            request.setAttribute("error", "Failed to fetch all sales");
            logger.error(e.getMessage() + " for getting all sales");
            return JspPages.ERROR_PAGE;
        }
    }
}
