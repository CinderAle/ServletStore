package by.bsuir.servletstore.entities;

public class Coupon {
    private final int id;
    private final String name;
    private final float sale;

    public Coupon(int id, String name, float sale) {
        this.id = id;
        this.name = name;
        this.sale = sale;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getSale() {
        return sale;
    }
}
