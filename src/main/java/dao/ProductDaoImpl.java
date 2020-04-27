package dao;

import api.ProductDao;
import entity.Product;
import entity.parser.ProductParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * package dao symbolize classes that are responsible for relation with database
 */
public class ProductDaoImpl implements ProductDao {
        private final String FILE_NAME ="product.data";
        private static ProductDaoImpl instance=null;

    private ProductDaoImpl() {
        File file=new File(FILE_NAME);
        try{
            file.createNewFile();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static ProductDaoImpl getInstance(){
        if(instance==null){
            instance=new ProductDaoImpl();
        }
        return instance;
    }

    public void saveProduct(Product product) throws IOException {
        List <Product> products=new ArrayList<>();
        products.add(product);
        saveProducts(products);
    }

    public void saveProducts(List<Product> products) throws IOException {
        List <Product> existingProductsInList=getAllProducts();
        existingProductsInList.addAll(products);
       PrintWriter printWriter=new PrintWriter(FILE_NAME);
       for(Product product:existingProductsInList){
           printWriter.write(product.toString());
           printWriter.write(System.lineSeparator());
       }
       printWriter.close();
    }

    public void removeProductById(Long productId) throws IOException {
        List<Product> existedProducts=getAllProducts();
        for (Product product:existedProducts){
            if(product.getId().equals(productId)){
                existedProducts.remove(product);
                break;
            }
        }
        clearFile(FILE_NAME);
        saveProducts(existedProducts);
    }

    public void removeProductsByName(String productName) throws IOException {
        List<Product> existedProducts=getAllProducts();
        for (Product product:existedProducts){
            if(product.getProductName().equals(productName)){
                existedProducts.remove(product);
                break;
            }
        }
        clearFile(FILE_NAME);
        saveProducts(existedProducts);
    }

    public List<Product> getAllProducts() throws IOException {
        List <Product> products= new ArrayList<>();
        BufferedReader bufferedReader=new BufferedReader(new FileReader(this.FILE_NAME));
        String productStr=bufferedReader.readLine();
        while (productStr!=null){
            Product product=ProductParser.stringToProduct(productStr);
            if(product!=null){
                products.add(product);
            }
            productStr=bufferedReader.readLine();
        }
        bufferedReader.close();

        return products;
    }

    public Product getProductById(Long Id) throws IOException {
        List <Product> productList=getAllProducts();
        for (Product product:productList) {
            if(product.getId().equals(Id)){
                return product;
            }
        }
        return null;
    }

    public Product getProductByName(String productName) throws IOException {
        List <Product> productList=getAllProducts();
        for (Product product:productList) {
            if(product.getProductName().equals(productName)){
                return product;
            }
        }
        return null;
    }

    public void clearFile(String fileName) throws IOException{
        PrintWriter printWriter=new PrintWriter(fileName);
        printWriter.close();
    }
}
