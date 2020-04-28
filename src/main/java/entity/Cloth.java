package entity;

import entity.enums.Color;
import entity.enums.Material;
import entity.enums.ProductSeparators;

public class Cloth extends Product{

    private String size;
    private Material material;

    public Cloth(Long id, String productName, double price, double weight, int productCount, Color color, String size, Material material) {
        super(id, productName, price, weight, productCount, color);
        this.size = size;
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        String sep=ProductSeparators.PRODUCT_SEPARATORS.toString();
        return ProductSeparators.CLOTH_ID +sep+getBasicProductString()+sep+size+sep+material;
    }
}
