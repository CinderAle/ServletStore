package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetProductsTaskTest {
    private HttpServletRequest request;
    private GetProductsTask getProductsTask;

    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
        getProductsTask = new GetProductsTask();
    }

    @Test
    void testRunning() throws TaskException {
        String page = getProductsTask.run(request);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> objectCaptor = ArgumentCaptor.forClass(Object.class);
        verify(request, times(3)).setAttribute(nameCaptor.capture(), objectCaptor.capture());
        assertEquals(JspPages.CATALOGUE_PAGE, page);
        assertEquals("productsList", nameCaptor.getValue());
        assertTrue(objectCaptor.getValue() instanceof List<?>);
    }
}