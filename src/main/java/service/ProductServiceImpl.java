package service;

import api.ProductService;
import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    List<Product> productList;

    public ProductServiceImpl() {
        productList=new ArrayList<Product>();
    }

    public ProductServiceImpl(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getAllProducts() {
        return this.productList;
    }

    public int countAllProducts() {
        return this.productList.size();
    }

    public boolean isProductExistByProductName(String productNameStr) {
        for (Product products:productList
             ) {
            if(products.getProductName().equals(productNameStr)){
                return true;
            }
        }
        return false;
    }

    public boolean isProductExistByID(Long productID) {
        for(Product product:productList){
            if(product.getId()==productID){
                return true;
            }
        }
        return false;
    }

    public Product getProductByName(String productName) {
        for (Product product:productList){
            if(product.getProductName().equals(productName)){
                return product;
            }
        }
        return null;
    }

    public boolean isOnWareHouse(String productName) {
        if(isProductExistByProductName(productName)){
            Product pr= getProductByName(productName);
            if(pr.getProductCount()>1){
                return true;
            }return false;
        }
        return false;
    }


}
