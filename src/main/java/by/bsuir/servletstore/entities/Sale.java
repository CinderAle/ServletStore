package by.bsuir.servletstore.entities;

public class Sale {
    private final int productId;
    private final float sale;

    public Sale(int productId, float sale) {
        this.productId = productId;
        this.sale = sale;
    }

    public int getProductId() {
        return productId;
    }

    public float getSale() {
        return sale;
    }
}
