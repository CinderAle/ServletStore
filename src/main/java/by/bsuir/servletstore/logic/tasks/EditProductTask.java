package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class EditProductTask implements ITask {
    private static final Logger logger = Logger.getLogger(EditProductTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
        String productImage = request.getParameter("productImage");
        try {
            productsDAO.editProduct(Integer.parseInt(productId), productName, productImage, Float.parseFloat(productPrice));
            return JspPages.CATALOGUE_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to edit the product!");
            logger.error(e.getMessage() + " for editing product " + productId);
            return JspPages.CATALOGUE_PAGE;
        }
    }
}
