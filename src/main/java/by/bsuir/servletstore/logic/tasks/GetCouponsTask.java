package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.entities.Coupon;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.util.List;

public class GetCouponsTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetCouponsTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        try {
            List<Coupon> coupons = productsDAO.getAllCoupons();
            request.setAttribute("coupons", coupons);
            return JspPages.ADMIN_SALES_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to fetch all coupons!");
            logger.error(e.getMessage() + "for getting all coupons");
            return JspPages.ERROR_PAGE;
        }
    }
}
