package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class AddProductTask implements ITask {
    private static final Logger logger = Logger.getLogger(AddProductTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String productName = request.getParameter("newProductName");
        String productPrice = request.getParameter("newProductPrice");
        String productImage = request.getParameter("newProductImage");
        try {
            if(productsDAO.addProduct(productName, productImage, Float.parseFloat(productPrice)) > 0) {
                return new GetUsersTask().run(request);
            }
            throw new RuntimeException("Could not add the product!");
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to add the product!");
            logger.error(e.getMessage() + " for " + productName);
            return JspPages.ADMIN_PANEL_PAGE;
        }
    }
}
