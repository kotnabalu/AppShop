package api;

import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLenghtLoginException;
import exception.UserShortLenghtPasswordException;

import java.io.IOException;
import java.util.List;
/**
 * Interfejs dla klas odpowiedzialnych za relację userów z bazą danych
 */
public interface UserDao {
    void saveUser(User user) ;

    List<User> getAllUsers();

    User getUserByLogin(String login);

    User getUserById(Long id);

    void removeUserByLogin(String login);

    void removeUserById(Long id);

    boolean isUserExist(String login);


}
