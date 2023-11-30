package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;

public class ChangeLanguageTask implements ITask {
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        String language = request.getParameter("language");
        request.getSession().setAttribute("lang", language);
        return request.getHeader("Referer");
    }
}
