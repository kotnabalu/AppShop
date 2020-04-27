package validator;

import entity.Product;
import exception.ProductCountNegativeException;
import exception.ProductNameEmptyException;
import exception.ProductPriceNoPositiveException;
import exception.ProductWeightNoPositiveException;

public class ProductValidator {
    private static ProductValidator instance=null;

    private ProductValidator() {
    }

    public ProductValidator getInstance(){
        if(instance==null){
            instance=new ProductValidator();
        }
        return instance;
    }

    public static boolean isProductValide(Product product) throws ProductCountNegativeException,ProductNameEmptyException,ProductPriceNoPositiveException,ProductWeightNoPositiveException{
        if(isNameValid(product.getProductName())){
            throw new ProductNameEmptyException("Product has empty name.");
        }
        if(!isCountValid(product.getProductCount())){
            throw new ProductCountNegativeException("Amount of product is less than 0.");
        }
        if(!isPriceValid(product.getPrice())){
            throw new ProductPriceNoPositiveException("Price cannot be negative.");
        }
        if(!isWeightValid(product.getWeight())){
            throw new ProductWeightNoPositiveException("Product has to be more than 0.");
        }
        return true;
    }

    private static boolean isNameValid(String productName){
        return productName.length()==0;
    }

    private static boolean isCountValid(int count){
        return count>=0;
    }
    private static boolean isPriceValid(double price){
        return price>0.0;
    }
    private static boolean isWeightValid(double weight){
        return weight>0.0;
    }
}
