package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.entities.User;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class RemoveUserCouponTask implements ITask {
    private static final Logger logger = Logger.getLogger(RemoveUserCouponTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            productsDAO.removeUserCoupon(user.getId());
            return new GetUserCartTask().run(request);
        }
        catch (RuntimeException e) {
            request.setAttribute("error", "Failed to delete the coupon!");
            logger.error(e.getMessage() + " for removing coupon of " + user.getId());
            return new GetUserCartTask().run(request);
        }
    }
}
