package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class RemoveCouponTask implements ITask {
    private static final Logger logger = Logger.getLogger(RemoveCouponTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String couponId = request.getParameter("couponId");
        try {
            productsDAO.removeCoupon(Integer.parseInt(couponId));
            return new GetCouponsTask().run(request);
        }
        catch (RuntimeException e) {
            request.setAttribute("error", "Failed to remove the coupon!");
            logger.error(e.getMessage() + " for removing coupon " + couponId);
            return JspPages.ADMIN_SALES_PAGE;
        }
    }
}
