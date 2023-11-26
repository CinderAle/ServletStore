package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class AddUserCouponTask implements ITask {
    private static final Logger logger = Logger.getLogger(AddUserCouponTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String userId = request.getParameter("userId");
        String coupon = request.getParameter("coupon");
        try {
            productsDAO.addUserCoupon(Integer.parseInt(userId), Integer.parseInt(coupon));
            return JspPages.CART_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to add the coupon!");
            logger.error(e.getMessage() + " for adding coupon " + coupon);
            return JspPages.CART_PAGE;
        }
    }
}
