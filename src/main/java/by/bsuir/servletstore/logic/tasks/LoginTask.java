package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.AuthDAO;
import by.bsuir.servletstore.dao.implementaion.StoreAuthDAO;
import by.bsuir.servletstore.entities.User;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class LoginTask implements ITask {
    private static final Logger logger = Logger.getLogger(LoginTask.class);
    private final AuthDAO authDAO = new StoreAuthDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = authDAO.getUser(email, password);
            if(user.isBanStatus()) {
                throw new RuntimeException("It seems you have banned from our platform");
            }
            request.getSession().setAttribute("user", user);
            request.setAttribute("loginFlag", true);
            return JspPages.LOGIN_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            logger.error(e.getMessage() + " for " + email);
            return JspPages.LOGIN_PAGE;
        }
    }
}
