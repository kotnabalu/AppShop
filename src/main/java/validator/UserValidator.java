package validator;

import entity.User;
import exception.UserShortLenghtLoginException;
import exception.UserShortLenghtPasswordException;

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

        if(!isPasswordValidated(user.getPassword())){
            throw new UserShortLenghtPasswordException(user.getPassword()+" - password is too short.");
        }
        return true;
    }

    private boolean isPasswordValidated(String password) {
        boolean valid=true;
        if(password.length()<6 || password.length() > 15){
            return false;
        }
        //Password should contain at least one upper case letter
        Pattern pattern=Pattern.compile(".*[A-Z].*");
        Matcher matcher=pattern.matcher(password);
        if (!matcher.matches()){
            return false;
        }
        //Password should contain at least one lower case letter
        Pattern pattern1=Pattern.compile(".*[a-z].*");
        Matcher matcher1=pattern1.matcher(password);
        if(!matcher1.matches()){
            return false;
        }
        //Password should contain at lease one number
        Pattern pattern2=Pattern.compile(".*[0-9].*");
        Matcher matcher2=pattern2.matcher(password);
        if(!matcher2.matches()){
            return false;
        }

        return valid;
    }

    private boolean isLoginSizeEnough(String userLogin){
        Pattern pattern=Pattern.compile(".{4,}");
        Matcher matcher=pattern.matcher(userLogin);
        return matcher.matches();
    }


}
