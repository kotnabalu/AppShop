package facade;

import api.UserRegisterLoginFacade;
import api.UserService;
import entity.User;
import service.UserServiceImpl;
import validator.UserValidator;

public class UserRegisterLoginFacadeImpl implements UserRegisterLoginFacade {
    UserService userService=UserServiceImpl.getInstance();
   private static UserRegisterLoginFacade instance=null;

    private UserRegisterLoginFacadeImpl() {
    }

    public static UserRegisterLoginFacade getInstance(){
        if(instance==null){
            instance=new UserRegisterLoginFacadeImpl();
        }
        return instance;
    }

    @Override
    public boolean registerUser(User user) {
        return userService.addUser(user);
    }

    @Override
    public boolean loginUser(String login, String password) {
        return userService.isUserPasswordValidWithLogin(login,password);
    }
}
