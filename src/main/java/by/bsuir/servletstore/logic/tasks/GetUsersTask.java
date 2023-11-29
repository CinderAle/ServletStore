package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.UserControlDAO;
import by.bsuir.servletstore.dao.implementaion.StoreUserControlDAO;
import by.bsuir.servletstore.entities.User;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.util.List;

public class GetUsersTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetUsersTask.class);
    private final UserControlDAO userControlDAO = new StoreUserControlDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        try {
            List<User> users = userControlDAO.getAllUsers();
            request.setAttribute("usersList", users);
            return JspPages.ADMIN_PANEL_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to get all users!");
            logger.error(e.getMessage() + " for getting all users");
            return JspPages.ADMIN_PANEL_PAGE;
        }
    }
}
