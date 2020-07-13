package facade;

import api.UserRegisterLoginFacade;
import api.UserService;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLenghtLoginException;
import exception.UserShortLenghtPasswordException;
import service.UserServiceImpl;

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
    public String registerUser(User user) {
        try{
            userService.addUser(user);
            return "Registered user";
        }catch (UserLoginAlreadyExistException e){
        e.printStackTrace();
        return e.getMessage();
        }catch (UserShortLenghtPasswordException e){
            e.printStackTrace();
            return e.getMessage();
        } catch (UserShortLenghtLoginException e){
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @Override
    public boolean loginUser(String login, String password) {
        return userService.isUserPasswordValidWithLogin(login,password);
    }
}
