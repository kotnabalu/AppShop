package entity.parser;

import entity.Separator;
import entity.User;

public class UserParser implements Separator {
   public static User stringToUser(String userStr){
        String [] userInfo=userStr.split(SEPARATOR);
        Long id= Long.parseLong(userInfo[0]);
        String login=userInfo[1];
        String password=userInfo[2];
        return new User(id,login,password);
    }
}
