package by.bsuir.servletstore.logic;

import jakarta.servlet.http.HttpServletRequest;

/*
 * Interface for tasks (commands)
 * @author CinderAle
 * @version 1.0
 * */
public interface ITask {
    /*
    * Method for running the task
    * @param request object of request
    * @return page
    * */
    String run(HttpServletRequest request) throws TaskException;
}
