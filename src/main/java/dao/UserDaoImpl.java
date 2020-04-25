package dao;

import api.UserDao;
import entity.User;
import entity.parser.UserParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final String FILENAME;

    public UserDaoImpl(String FILENAME) {
        this.FILENAME = FILENAME;
    }


    @Override
    public void saveUser(User user) throws IOException {
        List<User> users=new ArrayList<>();
        users.add(user);
        saveUsers(users);
    }

    @Override
    public void saveUsers(List<User> users) throws IOException {
        List<User> existedUsers=getAllUsers();
        existedUsers.addAll(users);
        PrintWriter printWriter=new PrintWriter(FILENAME);
        for (User user: existedUsers) {
          printWriter.write(user.toString());
          printWriter.write(System.lineSeparator());
        }
        printWriter.close();
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        // TODO
        // remember to clear the file before saving!
        List <User> users=new ArrayList<>();
        BufferedReader bufferedReader=new BufferedReader(new FileReader(FILENAME));
        String userStr="";
        while (userStr!=null){
            userStr=bufferedReader.readLine();
            if(userStr!=null){
                User user= UserParser.stringToUser(userStr);
                users.add(user);
            }
        }
        bufferedReader.close();

        return users;
    }

    @Override
    public User getUserByLogin(String login) throws IOException {
        List <User> existedUsers=getAllUsers();
        for (User user: existedUsers) {
            if(user.getLogin().equals(login)){
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserById(Long id) throws IOException {
        List <User> existedUsers=getAllUsers();
        for (User user: existedUsers) {
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void removeUserByLogin(String login) throws IOException {
        List <User> existingUser=getAllUsers();
        for (User user:existingUser
             ) {
            if(user.getLogin().equals(login)){
                existingUser.remove(user);
                break;
            }
        }
        clearFile(FILENAME);
        saveUsers(existingUser);
    }

    public void clearFile(String fileName) throws IOException{
        PrintWriter printWriter=new PrintWriter(fileName);
        printWriter.close();
    }

    @Override
    public void removeUserById(Long id) throws IOException {
        List <User> existingUser=getAllUsers();
        for (User user:existingUser
        ) {
            if(user.getId().equals(id)){
                existingUser.remove(user);
                break;
            }
        }
        clearFile(FILENAME);
        saveUsers(existingUser);
    }
}
