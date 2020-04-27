package service;

import api.UserDao;
import api.UserService;
import dao.UserDaoImpl;
import entity.User;
import exception.UserLoginAlreadyExistException;
import validator.UserValidator;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = UserDaoImpl.getInstance();
    private static UserServiceImpl instance = null;
    private UserValidator userValidator = UserValidator.getInstance();

    private UserServiceImpl() {

    }


    public static UserServiceImpl getInstance() {
        if (instance == null)
            instance = new UserServiceImpl();
        return instance;
    }

    public List<User> getAllUsers() throws IOException {
        return userDao.getAllUsers();
    }

    public boolean addUser(User user)   {
        try{
            if(isLoginExist(user.getLogin()))
                throw new UserLoginAlreadyExistException();
            if(userValidator.isValidate(user)) {
                userDao.saveUser(user);
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;

    }

    public void removeById(Long userId) throws IOException {
        userDao.removeUserById(userId);
    }

    private boolean isLoginExist(String login){
     User user=getUserByLogin(login);
     return user!=null;
    }

    public User getUserByLogin(String login) {
        List<User> userList;
        try{
            userList=getAllUsers();
        for (User user : userList) {
            boolean isFound = user.getLogin().equals(login);
            if (isFound) {
                return user;
            }
        }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(Long id) throws IOException {
        List <User> userList;

            userList=getAllUsers();
            for (User user:userList){
                boolean isFound=user.getId().equals(id);
                if(isFound){
                    return user;
                }
            }

        return null;
    }

    public boolean isUserPasswordValidWithLogin(String login,String password){
        User user=getUserByLogin(login);
        if(user==null)
            return false;

       boolean isLoginOk=user.getLogin().equals(login);
       boolean isPasswordOk=user.getPassword().equals(password);

       return isLoginOk && isPasswordOk;
    }
}


