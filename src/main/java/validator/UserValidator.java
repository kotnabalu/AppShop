package validator;

import api.UserDao;
import dao.UserDaoImpl;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLenghtLoginException;
import exception.UserShortLenghtPasswordException;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static UserValidator instance=null;

    private UserValidator() {
    }

    public static UserValidator getInstance(){
        if(instance==null){
            instance=new UserValidator();
        }
        return instance;
    }

    public boolean isValidate(User user)throws UserShortLenghtLoginException,UserShortLenghtPasswordException{
        if(!isLoginSizeEnough(user.getLogin())) {
            throw new UserShortLenghtLoginException(user.getLogin() + " - this login is too short.");
        }

        if(!isPasswordLongEnough(user.getPassword())){
            throw new UserShortLenghtPasswordException(user.getPassword()+" - password is too short.");
        }
        return true;
    }

    private boolean isPasswordLongEnough(String password) {
        Pattern pattern=Pattern.compile("[a-zA-Z]{6,}");
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isLoginSizeEnough(String userLogin){
        Pattern pattern=Pattern.compile("[a-zA-z]{4,}");
        Matcher matcher=pattern.matcher(userLogin);
        return matcher.matches();
    }


}
