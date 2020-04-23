package service;

import api.UserService;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    List<User> users;

    public UserServiceImpl() {
        users=new ArrayList<User>();
    }

    public UserServiceImpl(List<User> users) {
        this.users = users;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeById(Long userId) {
        for(int i=0;i<users.size();i++){
            User user=users.get(i);
            if(user.getId()==userId){
                users.remove(user);
                break;
            }

        }
    }


}
