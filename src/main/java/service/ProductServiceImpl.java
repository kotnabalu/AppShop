package service;

import api.ProductDao;
import api.ProductService;
import dao.ProductDaoImpl;
import entity.Product;
import validator.ProductValidator;

import java.io.IOException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl instance=null;
    private ProductDao productDao=ProductDaoImpl.getInstance();

    private ProductServiceImpl() {

    }
    public static ProductServiceImpl getInstance(){
        if(instance==null){
            instance=new ProductServiceImpl();
        }
        return instance;
    }

    public List<Product> getAllProducts() throws IOException {
        return productDao.getAllProducts();
    }

    public int countAllProducts() throws IOException {
        return productDao.getAllProducts().size();
    }

    public boolean isProductExistByProductName(String productNameStr) {
        Product product=null;
        try{
            product=productDao.getProductByName(productNameStr);
        }catch (IOException e){
            e.printStackTrace();
        }

        return product != null;
    }

    public boolean isProductExistByID(Long productID) {
        Product product=null;
        try{
            product=productDao.getProductById(productID);
        }catch (IOException e){
            e.printStackTrace();
        }

        return product==null;
    }

    public Product getProductByName(String productName) throws IOException {
       return productDao.getProductByName(productName);
    }

    public boolean isOnWareHouse(String productName) {
        Product product=null;
        if(isProductExistByProductName(productName)){
            try {
                product = getProductByName(productName);
            }catch (IOException e){
                e.printStackTrace();
            }
            if(product.getProductCount()>1){
                return true;
            }return false;
        }
        return false;
    }

    @Override
    public boolean saveProduct(Product product) {
        if(isProductExistByProductName(product.getProductName())){
            return false;
        }
        try{
            if(ProductValidator.isProductValide(product)){
                productDao.saveProduct(product);
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;

    }

}
