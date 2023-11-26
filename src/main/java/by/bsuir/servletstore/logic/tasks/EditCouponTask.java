package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class EditCouponTask implements ITask {
    private static final Logger logger = Logger.getLogger(EditCouponTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String couponId = request.getParameter("couponId");
        String newDiscount = request.getParameter("newCouponSale");
        String newName = request.getParameter("newCouponName");
        try {
            productsDAO.editCoupon(Integer.parseInt(couponId), newName, Float.parseFloat(newDiscount));
            return JspPages.ADMIN_SALES_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to add the coupon!");
            logger.error(e.getMessage() + " for coupon " + couponId);
            return JspPages.ADMIN_SALES_PAGE;
        }
    }
}
