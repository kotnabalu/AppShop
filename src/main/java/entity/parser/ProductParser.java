package entity.parser;

import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.Separator;


public class ProductParser implements Separator {
    public static Product stringToProduct(String productStr) {
        final char productType=productStr.charAt(0);
        switch (productType){
            case Product.PRODUCT_TYPE:
                return convertToProduct(productStr);
            case Cloth.PRODUCT_TYPE:
                return convertToCloth(productStr);
            case Boots.PRODUCT_TYPE:
                return convertToBoots(productStr);
        }
        return null;
    }

    private static Product convertToProduct(String productStr) {
        String[] productInfo = productStr.split(SEPARATOR);
        Long id = Long.parseLong(productInfo[1]);
        String name = productInfo[2];
        double price = Double.parseDouble(productInfo[3]);
        double weight = Double.parseDouble(productInfo[4]);
        int productCount = Integer.parseInt(productInfo[5]);
        String productColor=productInfo[6];
        return new Product(id, name, price, weight, productCount,productColor);
    }

    private static Boots convertToBoots(String productStr) {
        String[] productInfo = productStr.split(SEPARATOR);
        Long id = Long.parseLong(productInfo[1]);
        String name = productInfo[2];
        double price = Double.parseDouble(productInfo[3]);
        double weight = Double.parseDouble(productInfo[4]);
        int productCount = Integer.parseInt(productInfo[5]);
        String productColor=productInfo[6];
        int size = Integer.parseInt(productInfo[7]);
        boolean isNaturalSkin = Boolean.parseBoolean(productInfo[8]);

        return new Boots(id,name,price,weight,productCount,productColor,size,isNaturalSkin);
    }

    private static Cloth convertToCloth(String productStr) {
        String[] productInfo = productStr.split(SEPARATOR);
        Long id = Long.parseLong(productInfo[1]);
        String name = productInfo[2];
        double price = Double.parseDouble(productInfo[3]);
        double weight = Double.parseDouble(productInfo[4]);
        int productCount = Integer.parseInt(productInfo[5]);
        String productColor=productInfo[6];
        String size=productInfo[7];
        String material=productInfo[8];

        return new Cloth(id,name,price,weight,productCount,productColor,size,material);
    }
}
