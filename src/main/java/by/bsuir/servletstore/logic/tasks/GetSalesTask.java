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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetSalesTask implements ITask {
    private static final Logger logger = Logger.getLogger(GetSalesTask.class);
    private final ProductsDAO productsDAO = new StoreProductsDAO();
    @Override
    public String run(HttpServletRequest request) throws TaskException {
        try {
            List<Sale> sales = productsDAO.getAllSales();
            Map<Product, Sale> saleMap = new LinkedHashMap<>();
            for(Sale sale : sales) {
                Product product = productsDAO.getProduct(sale.getProductId());
                saleMap.put(product, sale);
            }

            List<Product> allProducts = productsDAO.getAllProducts();
            List<Product> notSaleProducts = new ArrayList<>();
            for(Product product : allProducts) {
                boolean saleFound = false;
                for(Sale sale : sales) {
                    if(sale.getProductId() == product.getId()) {
                        saleFound = true;
                        break;
                    }
                }
                if(!saleFound) {
                    notSaleProducts.add(product);
                }
            }

            request.setAttribute("sales", saleMap);
            request.setAttribute("notSaleProducts", notSaleProducts);
            return JspPages.ADMIN_SALES_PAGE;
        }
        catch (RuntimeException e) {
            request.setAttribute("error", "Failed to fetch all sales");
            logger.error(e.getMessage() + " for getting all sales");
            return JspPages.ERROR_PAGE;
        }
    }
}
