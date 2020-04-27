package api;

import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLenghtLoginException;
import exception.UserShortLenghtPasswordException;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> getAllUsers() throws IOException;
    boolean addUser(User user);
    void removeById(Long userId) throws IOException;
   boolean isUserPasswordValidWithLogin(String login,String password);
    User getUserById(Long id) throws IOException;
    User getUserByLogin(String login);

}
