package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.entities.Coupon;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class GetCouponTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetCouponTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String couponName = request.getParameter("couponName");
        try {
            Coupon coupon = productsDAO.getCoupon(couponName);
            request.setAttribute("coupon", coupon);
            return JspPages.CART_PAGE;
        }
        catch (RuntimeException e) {
            request.setAttribute("error", "Failed to get the coupon!");
            logger.error(e.getMessage() + " for getting coupon " + couponName);
            return JspPages.CART_PAGE;
        }
    }
}
