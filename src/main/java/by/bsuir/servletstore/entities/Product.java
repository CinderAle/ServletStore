package by.bsuir.servletstore.entities;

public class Product {
    private final String name;
    private final String imagePath;
    private final float price;
    private final int id;

    public Product(String name, String imagePath, float price, int id) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public float getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
