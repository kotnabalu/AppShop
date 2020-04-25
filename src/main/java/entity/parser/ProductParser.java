package entity.parser;

import com.sun.org.apache.bcel.internal.generic.RET;
import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.Separator;


public class ProductParser implements Separator {
    public static Product stringToProduct(String productStr, String productType) {
        if (productType.equals("PRODUCT")) {
            return convertToProduct(productStr);
        }
        if (productType.equals("BOOTS")) {
            return convertToBoots(productStr);
        }
        if (productType.equals("CLOTH")) {
            return convertToCloth(productStr);
        }
        return null;
    }

    protected static Product convertToProduct(String productStr) {
        String[] productInfo = productStr.split(SEPARATOR);
        Long id = Long.parseLong(productInfo[0]);
        String name = productInfo[1];
        double price = Double.parseDouble(productInfo[2]);
        double weight = Double.parseDouble(productInfo[3]);
        int productCount = Integer.parseInt(productInfo[4]);
        return new Product(id, name, price, weight, productCount);
    }

    protected static Boots convertToBoots(String productStr) {
        String[] productInfo = productStr.split(SEPARATOR);
        Long id = Long.parseLong(productInfo[0]);
        String name = productInfo[1];
        double price = Double.parseDouble(productInfo[2]);
        double weight = Double.parseDouble(productInfo[3]);
        int productCount = Integer.parseInt(productInfo[4]);
        int size = Integer.parseInt(productInfo[5]);
        boolean isNaturalSkin = Boolean.parseBoolean(productInfo[6]);

        return new Boots(id,name,price,weight,productCount,size,isNaturalSkin);
    }

    protected static Cloth convertToCloth(String productStr) {
        String[] productInfo = productStr.split(SEPARATOR);
        Long id = Long.parseLong(productInfo[0]);
        String name = productInfo[1];
        double price = Double.parseDouble(productInfo[2]);
        double weight = Double.parseDouble(productInfo[3]);
        int productCount = Integer.parseInt(productInfo[4]);
        String size=productInfo[5];
        String material=productInfo[6];

        return new Cloth(id,name,price,weight,productCount,size,material);
    }
}
