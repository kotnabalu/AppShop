package entity;

public class Boots extends Product implements Separator {
    public static final char PRODUCT_TYPE = 'B';

    private int size;
    private boolean isNatutalSkin;

    public Boots(Long id, String productName, double price, double weight, int productCount, String color, int size, boolean isNatutalSkin) {
        super(id, productName, price, weight, productCount, color);
        this.size = size;
        this.isNatutalSkin = isNatutalSkin;
    }

    public int getSize() {
        return size;
    }

    public boolean isNatutalSkin() {
        return isNatutalSkin;
    }

    @Override
    public String toString() {
        return PRODUCT_TYPE + SEPARATOR + getBasicProductString() + SEPARATOR + size + SEPARATOR + isNatutalSkin;
    }
}
