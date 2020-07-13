package entity;

import entity.enums.Color;

import java.util.Objects;

public class Product  {

    private Long id;
    private String productName;
    private double price;
    private double weight;
    private int productCount;
    private Color color;
    private long user_id;

    public Product(Long id, String productName, double price, double weight, int productCount, Color color) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.productCount = productCount;
        this.color = color;
    }

    public Product(Long id, String productName, double price, double weight, int productCount, Color color, long user_id) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.productCount = productCount;
        this.color = color;
        this.user_id= user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }



    public long getUser_id() {
        return user_id;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public int getProductCount() {
        return productCount;
    }

    public Color getColor() {
        return color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", productCount=" + productCount +
                ", color=" + color +
                ", user_id=" + user_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Double.compare(product.weight, weight) == 0 &&
                productCount == product.productCount &&
                Objects.equals(id, product.id) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, weight, productCount, color);
    }
}
