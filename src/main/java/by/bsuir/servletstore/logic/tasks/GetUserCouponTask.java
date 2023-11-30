package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.entities.Coupon;
import by.bsuir.servletstore.entities.User;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class GetUserCouponTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetUserCouponTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        User user = (User) request.getSession().getAttribute("user");
        float totalSum = (float) request.getAttribute("totalSum");
        try {
            int couponId = productsDAO.getUserCouponId(user.getId());
            Coupon coupon = productsDAO.getCouponById(couponId);
            request.setAttribute("coupon", coupon);
            request.setAttribute("couponSum", String.format("%.2f", totalSum * (100 - coupon.getSale()) / 100));
            return JspPages.CART_PAGE;
        }
        catch (RuntimeException e) {
            request.setAttribute("coupon", null);
            logger.error(e.getMessage() + "no coupon for" + user.getId());
            return JspPages.CART_PAGE;
        }
    }
}
