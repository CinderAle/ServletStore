package by.bsuir.servletstore.logic.tasks;

import by.bsuir.servletstore.controller.JspPages;
import by.bsuir.servletstore.dao.ProductsDAO;
import by.bsuir.servletstore.dao.implementaion.StoreProductsDAO;
import by.bsuir.servletstore.entities.Product;
import by.bsuir.servletstore.entities.Sale;
import by.bsuir.servletstore.logic.ITask;
import by.bsuir.servletstore.logic.TaskException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import java.util.*;

public class GetProductsTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetProductsTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        try {
            Map<Product, Sale> saleMap = new LinkedHashMap<>();
            List<Product> products = productsDAO.getAllProducts();
            List<Sale> sales = productsDAO.getAllSales();
            boolean saleFound;
            for (Product product : products) {
                saleFound = false;
                for(Sale sale : sales) {
                    if(product.getId() == sale.getProductId()) {
                        saleMap.put(product, sale);
                        saleFound = true;
                        break;
                    }
                }
                if(!saleFound) {
                    saleMap.put(product, null);
                }
            }
            request.setAttribute("saleMap", saleMap);
            request.setAttribute("salesList", sales);
            request.setAttribute("productsList", products);
            return JspPages.CATALOGUE_PAGE;
        }
        catch(RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            logger.error(e.getMessage());
            return JspPages.ERROR_PAGE;
        }
    }
}
