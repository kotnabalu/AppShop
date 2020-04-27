package api;

import entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List <Product> getAllProducts() throws IOException;
    int countAllProducts() throws IOException;
    boolean isProductExistByProductName(String productName);
    boolean isProductExistByID(Long productID) throws IOException;
    Product getProductByName(String productName) throws IOException; // jeżeli nie istnieje, to null!
    boolean isOnWareHouse(String productName);

    boolean saveProduct(Product product);


}
