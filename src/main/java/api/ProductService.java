package api;

import entity.Product;

import java.util.List;

public interface ProductService {
    List <Product> getAllProducts();
    int countAllProducts();
    boolean isProductExistByProductName(String productName);
    boolean isProductExistByID(Long productID);
    Product getProductByName(String productName); // jeżeli nie istnieje, to null!
    boolean isOnWareHouse(String productName);


}
