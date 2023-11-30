package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class RemoveProductTask implements ITask {
    private static final Logger logger = Logger.getLogger(RemoveProductTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String productId = request.getParameter("productId");
        try {
            productsDAO.removeProduct(Integer.parseInt(productId));
            return new GetProductsTask().run(request);
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to delete the product!");
            logger.error(e.getMessage());
            return JspPages.CATALOGUE_PAGE;
        }
    }
}
