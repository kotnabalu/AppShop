package dao;

import api.UserDao;
import entity.User;
import validator.UserValidator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private String database = "shop";
    private String tableName = "users";
    private String user = "root";
    private String password = "admin";
    Connection connection;

    private static UserDaoImpl instance;


    public UserDaoImpl() {
        init();
    }

    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + database + "?useSSL=false", user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    /**
     * Saves user to database when validated
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        PreparedStatement statement = null;
        UserValidator userValidator = UserValidator.getInstance();

        try {
            if (userValidator.isValidate(user) && !isUserExist(user.getLogin())) {
                String query = "insert into " + tableName + " (login, password) values (?,?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.execute();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Ok
     *
     * @return All users in database
     */
    @Override
    public List<User> getAllUsers() {
        // TODO
        // remember to clear the file before saving!
        List<User> users = new LinkedList<>();
        Statement statement = null;
        try {
            String query = "select * from " + tableName;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                User user = new User(id, login, password);
                users.add(user);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * @param login
     * @return User
     */
    @Override
    public User getUserByLogin(String login) {
        List<User> existedUsers = getAllUsers();
        for (User user : existedUsers) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @param id
     * @return User
     */
    @Override
    public User getUserById(Long id) {
        List<User> existedUsers = getAllUsers();
        for (User user : existedUsers) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Removes User from database by Login
     * @param login
     */

    @Override
    public void removeUserByLogin(String login) {
        PreparedStatement statement = null;

        try {
            String query = "delete from " + tableName + " where login=?";
            statement = connection.prepareStatement(query);

            statement.setString(1, login);
            statement.execute();

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove User from database by ID
     * @param id
     */

    @Override
    public void removeUserById(Long id) {
        PreparedStatement statement;
        try{
            String query="delete from "+tableName+" where id='"+id+"'";
            statement=connection.prepareStatement(query);
            statement.execute(query);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Check if user exist in database
     *
     * @param login
     * @return true/false
     */
    public boolean isUserExist(String login) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }


}
