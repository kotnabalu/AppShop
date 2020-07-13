package dao;

import api.ProductDao;
import entity.Product;
import entity.User;
import entity.enums.Color;
import validator.ProductValidator;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private final String dataBase = "shop";
    private final String tableName = "products";
    private final String user = "root";
    private final String password = "admin";
    private static ProductDaoImpl instance;

    Connection connection;

    private ProductDaoImpl() {
        init();
    }

    public static ProductDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProductDaoImpl();
        }
        return instance;
    }


    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dataBase + "?useSSL=false", user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void saveProduct(Product product) throws IOException {
        PreparedStatement statement = null;

        try {
            if(ProductValidator.isProductValide(product)){
                String query = "insert into products (name, price, weight, count, color, user_id ) values (?,?,?,?,?,?)";
                statement=connection.prepareStatement(query);
                statement.setString(1,product.getProductName());
                statement.setDouble(2,product.getPrice());
                statement.setDouble(3,product.getWeight());
                statement.setInt(4,product.getProductCount());
                statement.setInt(5,product.getColor().ordinal());
                statement.setLong(6,product.getUser_id());
                statement.execute();
                statement.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void removeProductsByName(String productName, Long userId) throws IOException {
        PreparedStatement statement=null;

        try {
            String query ="delete from "+tableName+ " where user_id=? and name=?";
            statement= connection.prepareStatement(query);
            statement.setLong(1,userId);
            statement.setString(2, productName);
            statement.execute();
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @Override
    public List<Product> getAllProducts()  {
        Statement statement= null;
        List <Product> products = new ArrayList<>();
        String query = "select * from "+tableName;

        try {
            statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                long id= resultSet.getLong(1);
                String name= resultSet.getString(2);
                Double price = resultSet.getDouble(3);
                Double weight= resultSet.getDouble(4);
                int count= resultSet.getInt(5);
                Color color = Color.valueOf(resultSet.getString(6));
                long user_id= resultSet.getLong(7);

                Product product = new Product(id,name,price,weight,count,color,user_id);
                products.add(product);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }

    @Override
    public Product getProductById(Long Id) throws IOException {
        return null;
    }

    @Override
    public Product getProductByName(String productName) throws IOException {
        return null;
    }

    @Override
    public List<Product> getProductsByUserId(Long id)  {
       List <Product> products= new ArrayList<>();
       List <Product> allProducts = getAllProducts();

       for (Product product : allProducts){
           Long userId= Long.valueOf(product.getUser_id());
           if(userId.equals(id)){
               products.add(product);
           }
       }
       return products;
    }
}
