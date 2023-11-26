package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.AuthDAO;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreAuthDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.entities.Product;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.util.List;

public class RegisterTask implements ITask {
    private static final Logger logger = Logger.getLogger(RegisterTask.class);
    private final AuthDAO authDAO = new StoreAuthDAO();
    private static final int MAX_PASSWORD_LENGTH = 6;
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repassword");
        if(!password.equals(repeatedPassword)) {
            request.setAttribute("error", "Passwords must match!");
            return JspPages.REGISTER_PAGE;
        }
        if(password.length() < MAX_PASSWORD_LENGTH) {
            request.setAttribute("error", "The password is too short");
            return JspPages.REGISTER_PAGE;
        }
        try {
            int userId = authDAO.registerUser(name, email, password);
            return JspPages.CATALOGUE_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            logger.error(e.getMessage() + " for " + email);
            return JspPages.REGISTER_PAGE;
        }
    }
}
