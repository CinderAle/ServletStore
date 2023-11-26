package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.UserControlDAO;
import by.bsuir.servletstore.dao.implementaion.StoreUserControlDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class EditUserTask implements ITask {
    private static final Logger logger = Logger.getLogger(EditUserTask.class);
    private final UserControlDAO userControlDAO = new StoreUserControlDAO();

    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String userId = request.getParameter("userId");
        String banState = request.getParameter("userBanState");
        String userRole = request.getParameter("userRole");
        try {
            userControlDAO.setUserInfo(Integer.parseInt(userId), Integer.parseInt(userRole), Boolean.parseBoolean(banState));
            return JspPages.ADMIN_PANEL_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", "Failed to edit the user!");
            logger.error(e.getMessage() + "for editing user " + userId);
            return JspPages.ADMIN_PANEL_PAGE;
        }
    }
}
