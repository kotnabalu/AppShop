package dao;

import entity.Product;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProductDaoImplTest {
    private ProductDaoImpl productDao;
    private final String FILENAME = "Temp.txt";

    @Before
    public void setUp() {
        productDao = new ProductDaoImpl(FILENAME, "PRODUCT");
    }

    @Test
    public void testGetAllProductsWhileEmpty() throws IOException {
        productDao.clearFile(FILENAME);
        List<Product> listTakenFromTestClass = productDao.getAllProducts();
        assertEquals(0, listTakenFromTestClass.size());
    }

    @Test
    public void testSaveProducts() throws IOException {
        int sizeBefore = productDao.getAllProducts().size();
        List<Product> products = new ArrayList<>();
        products.add(new Product(15L, "Product1", 20.00, 0.20, 1));
        products.add(new Product(20L, "mleko", 2.50, 1, 1));
        productDao.saveProducts(products);
        int afterSize = productDao.getAllProducts().size();
        assertEquals(sizeBefore + 2, afterSize);
    }


    @Test
    public void testIsOneAdded() throws IOException {
        int sizeBefore = productDao.getAllProducts().size();
        Product product = new Product(7L, "pieczywo", 2.99, 0.7, 2);
        productDao.saveProduct(product);
        int afterSize = productDao.getAllProducts().size();
        assertEquals(sizeBefore + 1, afterSize);
    }

    @Test
    public void testRemoveProductByProductNamePositive() throws IOException {
        int sizeOfList = productDao.getAllProducts().size();
        productDao.saveProduct(new Product(10L, "kasza", 3.0, 1.0, 1));
        productDao.removeProductsByName("kasza");
        int sizeOfListAfter = productDao.getAllProducts().size();
        assertEquals(sizeOfList, sizeOfListAfter);
    }


    @Test
    public void testRemoveNonExistingProduct() throws IOException {
        int size = productDao.getAllProducts().size();
        productDao.removeProductsByName("niema");
        assertEquals(size, productDao.getAllProducts().size());
    }

    @Test
    public void testRemoveByIDExisting() throws IOException {
        int size = productDao.getAllProducts().size();
        productDao.saveProduct(new Product(22L, "maka", 5.0, 1.0, 1));
        productDao.removeProductById(22L);
        assertEquals(size, productDao.getAllProducts().size());
    }

    @Test
    public void testDontRemoveNonExistingByID() throws IOException {
        int size = productDao.getAllProducts().size();
        productDao.removeProductById(1555L);
        assertEquals(size, productDao.getAllProducts().size());
    }

    @Test
    public void testGetByIDPositive() throws IOException {
        Product product = new Product(100L, "milk", 20, 0.8, 1);
        productDao.saveProduct(product);
        Product takenFromList = productDao.getProductById(100L);
        assertEquals(product, takenFromList);
    }

    @Test
    public void testGetByIDNegative() throws IOException {
        Product takenFromLis = productDao.getProductById(2000L);
        assertNull(takenFromLis);
    }

    @Test
    public void testGetByNamePositive() throws IOException {
        Product product = new Product(10L, "butter", 20, 0.8, 1);
        productDao.saveProduct(product);
        Product takenFromList = productDao.getProductByName("butter");
        assertEquals(product, takenFromList);
    }

    @Test
    public void
    testGetByNameNegative() throws IOException {
        Product takenFromList = productDao.getProductByName("niema");
        assertNull(takenFromList);
    }

}
