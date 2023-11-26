package by.bsuir.servletstore.logic;

public class TaskException extends Exception {
    private Exception taskException;
    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, Exception exception) {
        super(message, exception);
        this.taskException = exception;
    }

    public Exception getTaskException() {
        return this.taskException;
    }
}
