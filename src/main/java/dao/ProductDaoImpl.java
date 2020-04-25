package dao;

import api.ProductDao;
import entity.Product;
import entity.parser.ProductParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
        private final String fileName;
        private final String productType;

    public ProductDaoImpl(String fileName, String productType) {
        this.fileName = fileName;
        this.productType = productType;
    }

    public void saveProduct(Product product) throws IOException {
        List <Product> products=new ArrayList<>();
        products.add(product);
        saveProducts(products);
    }

    public void saveProducts(List<Product> products) throws IOException {
        List <Product> existingProductsInList=getAllProducts();
        existingProductsInList.addAll(products);
       PrintWriter printWriter=new PrintWriter(fileName);
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
        clearFile(fileName);
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
        clearFile(fileName);
        saveProducts(existedProducts);
    }

    public List<Product> getAllProducts() throws IOException {
        List <Product> products=new ArrayList<Product>();
        BufferedReader bufferedReader=new BufferedReader(new FileReader(this.fileName));
        String productStr=bufferedReader.readLine();
        while (productStr!=null){
            Product product=ProductParser.stringToProduct(productStr,this.productType);
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
