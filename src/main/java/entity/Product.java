package entity;

public class Product {
    private int id;
    private String productName;
    private double price;
    private double weight;
    private int productCount;

    public Product(int id, String productName, double price, double weight, int productCount) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.productCount = productCount;
    }

    public int getId() {
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
                '}';
    }
}
