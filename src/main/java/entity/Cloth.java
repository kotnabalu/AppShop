package entity;

public class Cloth extends Product {
    private String size;
    private String material;

    public Cloth(Long id, String productName, double price, double weight, int productCount, String size, String material) {
        super(id, productName, price, weight, productCount);
        this.size = size;
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return super.toString()+ "Cloth{" +
                "size='" + size + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
