package entity.parser;

import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.enums.Color;
import entity.enums.Material;
import entity.enums.ProductSeparators;
import entity.enums.SkinType;


public class ProductParser {
    public static Product stringToProduct(String productStr) {
       final ProductSeparators productType=ProductSeparators.getIdByChar(productStr.substring(0,1));
        switch (productType){
            case PRODUCT_ID:
                return convertToProduct(productStr);
            case CLOTH_ID:
                return convertToCloth(productStr);
            case BOOTS_ID:
                return convertToBoots(productStr);
        }
        return null;
    }

    private static Product convertToProduct(String productStr) {
        String[] productInfo = productStr.split(ProductSeparators.PRODUCT_SEPARATORS.toString());
        Long id = Long.parseLong(productInfo[1]);
        String name = productInfo[2];
        double price = Double.parseDouble(productInfo[3]);
        double weight = Double.parseDouble(productInfo[4]);
        int productCount = Integer.parseInt(productInfo[5]);
        Color productColor=ColorParser.colorParser(productInfo[6]);
        return new Product(id, name, price, weight, productCount,productColor);
    }

    private static Boots convertToBoots(String productStr) {
        String[] productInfo = productStr.split(ProductSeparators.PRODUCT_SEPARATORS.toString());
        Long id = Long.parseLong(productInfo[1]);
        String name = productInfo[2];
        double price = Double.parseDouble(productInfo[3]);
        double weight = Double.parseDouble(productInfo[4]);
        int productCount = Integer.parseInt(productInfo[5]);
        Color productColor=ColorParser.colorParser(productInfo[6]);
        int size = Integer.parseInt(productInfo[7]);
        SkinType skiType=SkinParser.skinTypeParser(productInfo[8]);

        return new Boots(id,name,price,weight,productCount,productColor,size,skiType);
    }

    private static Cloth convertToCloth(String productStr) {
        String[] productInfo = productStr.split(ProductSeparators.PRODUCT_SEPARATORS.toString());
        Long id = Long.parseLong(productInfo[1]);
        String name = productInfo[2];
        double price = Double.parseDouble(productInfo[3]);
        double weight = Double.parseDouble(productInfo[4]);
        int productCount = Integer.parseInt(productInfo[5]);
        Color productColor=ColorParser.colorParser(productInfo[6]);
        String size=productInfo[7];
        Material material=MaterialParser.materialParser(productInfo[8]);

        return new Cloth(id,name,price,weight,productCount,productColor,size,material);
    }
}
