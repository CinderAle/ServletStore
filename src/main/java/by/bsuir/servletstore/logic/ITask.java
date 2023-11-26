package by.bsuir.servletstore.logic;

import jakarta.servlet.http.HttpServletRequest;

public interface ITask {
    String run(HttpServletRequest request) throws TaskException;
}
