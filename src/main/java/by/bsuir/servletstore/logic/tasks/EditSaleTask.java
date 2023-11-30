package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class EditSaleTask implements ITask {
    private static final Logger logger = Logger.getLogger(EditSaleTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String productId = request.getParameter("saleProductId");
        String newSale = request.getParameter("newSaleValue");
        try {
            productsDAO.editSale(Integer.parseInt(productId), Float.parseFloat(newSale));
            return new GetCouponsTask().run(request);
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to edit the sale!");
            logger.error(e.getMessage() + " for editing sale on " + productId);
            return JspPages.ADMIN_SALES_PAGE;
        }
    }
}
