package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.AuthDAO;
import by.bsuir.servletstore.dao.implementaion.StoreAuthDAO;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class LogoutTask implements ITask {
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        request.getSession().invalidate();
        return JspPages.LOGIN_PAGE;
    }
}
