package entity;

import entity.enums.Color;
import entity.enums.ProductSeparators;
import entity.enums.SkinType;

public class Boots extends Product {

    private int size;
    private SkinType skinType;

    public Boots(Long id, String productName, double price, double weight, int productCount, Color color, int size, SkinType skinType) {
        super(id, productName, price, weight, productCount, color);
        this.size = size;
        this.skinType = skinType;
    }

    public int getSize() {
        return size;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    @Override
    public String toString() {
        String sep=ProductSeparators.PRODUCT_SEPARATORS.toString();
        return ProductSeparators.BOOTS_ID + sep + getBasicProductString() + sep + size + sep + skinType;
    }
}
