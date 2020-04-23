package entity;

public class Boots extends Product {
    private int size;
    private boolean isNatutalSkin;

    public Boots(int id, String productName, double price, double weight, int productCount, int size, boolean isNatutalSkin) {
        super(id, productName, price, weight, productCount);
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
        return super.toString()+"Boots{" +
                "size=" + size +
                ", isNatutalSkin=" + isNatutalSkin +
                '}';
    }
}
