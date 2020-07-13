package entity.enums;


@Deprecated
public enum ProductSeparators {
    PRODUCT_SEPARATORS("#"),
    PRODUCT_ID("P"),
    CLOTH_ID("C"),
    BOOTS_ID("B");

    private String word;

    ProductSeparators(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return this.word;
    }

    public static ProductSeparators getIdByChar(String word){
        for(ProductSeparators productSeparators:ProductSeparators.values()){
            if(productSeparators.getWord().equals(word)){
                return productSeparators;
            }
        }
        return null;
    }
}
