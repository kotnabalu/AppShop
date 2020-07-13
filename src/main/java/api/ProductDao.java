package api;

import entity.Product;

import java.io.IOException;
import java.util.List;

/**
 * Interfejs dla klas odpowiedzialnych za relację produktów z bazą danych
 */

public interface ProductDao {
   void saveProduct(Product product) throws IOException;
    void removeProductsByName(String productName, Long userId) throws IOException;
    List <Product> getAllProducts() throws IOException;
    Product getProductById(Long Id) throws IOException;
    Product getProductByName(String productName) throws IOException;
    List <Product> getProductsByUserId(Long id) throws IOException;
}
