package service;

import entity.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest {
    ProductServiceImpl productService;
    List<Product> productsList;
    @Before
    public void setUp(){
        productsList=new ArrayList<Product>();
        productsList.add(new Product(1L,"maslo",4.99,0.25,1));
        productsList.add(new Product(2L,"mleko",2.49,2.0,3));
        productService=new ProductServiceImpl(new ArrayList<Product>(productsList));
    }

    @Test
    public void testGetAllProducts(){
        List <Product> listOfProductListTestClass=productService.getAllProducts();
        assertEquals(productsList,listOfProductListTestClass);
    }
    @Test
    public void testNegativeGetAllProducts(){
        List <Product> listOfPorcutsFromTestClass=productService.getAllProducts();
        productsList.add(new Product(3L,"jajka",10.0,0.5,1));
        assertNotEquals(productsList,listOfPorcutsFromTestClass);
    }

    @Test
    public void testCountAllProducts(){
        int result=productService.countAllProducts();
        assertEquals(2,result);
    }

    @Test
    public void testCountAllProductsWithoudProducts(){
        ProductServiceImpl productService1=new ProductServiceImpl();
        int result=productService1.countAllProducts();
        assertEquals(0,result);
    }
    @Test
    public void testisExistByPorductNamePositive(){
        boolean isOnList=productService.isProductExistByProductName("mleko");
        assertEquals(true,isOnList);
    }

    @Test
    public void testisExistByPorductNameNegative(){
        boolean isNotOnList=productService.isProductExistByProductName("kawa");
        assertNotEquals(true,isNotOnList);
    }

    @Test
    public void testisExistByProductIdPositive(){
        boolean isOnList=productService.isProductExistByID(1L);
        assertEquals(true,isOnList);
    }

    @Test
    public void testIsExistByProductIdNegative(){
        boolean isNotOnList=productService.isProductExistByID(4L);
       assertFalse(isNotOnList);
    }

    @Test
    public void testGetNamePositive(){
        Product woda=new Product(4l,"woda",1.99,1.0,1);
        productsList.add(woda);
        ProductServiceImpl productService1=new ProductServiceImpl(new ArrayList<Product>(productsList));
        Product wodaGetByTestClass=productService1.getProductByName("woda");
        assertEquals(woda,wodaGetByTestClass);
    }

    @Test
    public void testGetByNameNameNegative(){
        Product product=productService.getProductByName("ciastka");
        assertEquals(null,product);
    }

    @Test
    public void testIsOnWareHouse(){
        boolean isOnWarehouseTrue=productService.isOnWareHouse("mleko");
        assertTrue(isOnWarehouseTrue);
    }

    @Test
    public void testIsOnWareHouseNegative(){
        boolean isOnWarehouse=productService.isOnWareHouse("maslo");
        assertFalse(isOnWarehouse);
    }

    @Test
    public void testIsOnWareHouseWhenNonExist(){
        boolean isonWateHouseFalse=productService.isOnWareHouse("kot");
        assertFalse(isonWateHouseFalse);
    }


}
