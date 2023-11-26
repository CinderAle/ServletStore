package by.bsuir.servletstore.controller;

import java.io.*;

import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import by.bsuir.servletstore.logic.TaskSwitch;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class Controller extends HttpServlet {
    private static final String ERROR_CONTENT_TYPE = "text/html";
    private static final String ERROR_PAGE = "<h1 style='color:red;text-align:center; margin-top: 100px'>An error occurred!</h1>";
    private static final String PAGE_PATH = "/WEB-INF/pages/%s";
    private static final String PAGE_PARAMETER = "page";
    private static final String TASK_PARAMETER = "command";

    private void fetchErrorPage(HttpServletResponse response) throws IOException {
        response.setContentType(ERROR_CONTENT_TYPE);
        response.getWriter().println(ERROR_PAGE);
    }
    private void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String taskName = request.getParameter(TASK_PARAMETER);
        ITask task = TaskSwitch.getInstance().getTask(taskName);
        String page;
        try {
            page = task.run(request);
        }
        catch (TaskException te) {
            page = JspPages.ERROR_PAGE;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        if (requestDispatcher != null) {
            requestDispatcher.forward(request, response);
        }
        else {
            fetchErrorPage(response);
        }
    }

    public Controller() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = request.getParameter(PAGE_PARAMETER);
        if (page != null) {
            request.getRequestDispatcher(String.format(PAGE_PATH, page)).forward(request, response);
        } else {
            execute(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }
}