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

public class AddUserCouponTask implements ITask {
    private static final Logger logger = Logger.getLogger(AddUserCouponTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        User user = (User) request.getSession().getAttribute("user");
        String couponName = request.getParameter("couponName").toUpperCase();
        try {
            Coupon coupon = productsDAO.getCoupon(couponName);
            productsDAO.addUserCoupon(user.getId(), coupon.getId());
            return new GetUserCartTask().run(request);
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "No such coupon!");
            logger.error(e.getMessage() + " for adding coupon " + couponName);
            return new GetUserCartTask().run(request);
        }
    }
}
