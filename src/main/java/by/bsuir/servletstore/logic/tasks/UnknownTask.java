package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;

public class UnknownTask implements ITask {
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        return JspPages.ERROR_PAGE;
    }
}
