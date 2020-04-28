package entity.parser;

import entity.User;
import entity.enums.ProductSeparators;

public class UserParser {
   public static User stringToUser(String userStr){
        String [] userInfo=userStr.split(ProductSeparators.PRODUCT_SEPARATORS.toString());
        Long id= Long.parseLong(userInfo[0]);
        String login=userInfo[1];
        String password=userInfo[2];
        return new User(id,login,password);
    }
}
