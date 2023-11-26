package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class AddSaleTask implements ITask {
    private static final Logger logger = Logger.getLogger(AddSaleTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String product = request.getParameter("newProductSale");
        String sale = request.getParameter("newSaleSize");
        try {
            productsDAO.addSale(Integer.parseInt(product), Float.parseFloat(sale));
            return JspPages.ADMIN_SALES_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to add the sale!");
            logger.error(e.getMessage() + " for sale on " + product);
            return JspPages.ADMIN_SALES_PAGE;
        }
    }
}
