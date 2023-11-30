package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class AddCouponTask implements ITask {
    private static final Logger logger = Logger.getLogger(AddCouponTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String couponName = request.getParameter("newCouponName").toUpperCase();
        String couponSale = request.getParameter("newCouponSale");
        try {
            if(productsDAO.addCoupon(couponName, Float.parseFloat(couponSale)) > 0) {
                return new GetCouponsTask().run(request);
            }
            throw new RuntimeException("Could not add the coupon!");
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to add the coupon!");
            logger.error(e.getMessage() + " for " + couponName);
            return JspPages.ADMIN_SALES_PAGE;
        }
    }
}
