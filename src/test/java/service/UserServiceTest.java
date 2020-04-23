package service;

import entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    @Test
    public void testGetAllUsers(){
        //is
        List<User> users=new ArrayList<User>();
        users.add(new User(1l,"admin","admin"));
        users.add(new User(2l,"damian","damian111"));
        //then
        UserServiceImpl userService=new UserServiceImpl(users);
        List <User> userFromTestClass = userService.getAllUsers();
        //expected
        assertEquals(users,userFromTestClass);
    }

    @Test
    public void testAddUser(){
        List<User> users=new ArrayList<User>();
        User user=new User(1l,"admin","admin");
        users.add(user);

        UserServiceImpl userService=new UserServiceImpl();
        userService.addUser(user);
        List<User> usersFromTestClass=userService.getAllUsers();

        assertEquals(users,usersFromTestClass);
    }

    @Test
    public void testRemoveUser(){
        List<User> users=new ArrayList<User>();
        User user1=new User(1l,"admin","admin");
        User user2=new User(1l,"edyta","edyta");
        users.add(user1);
        users.add(user2);

        UserServiceImpl userService=new UserServiceImpl(users);
        users.remove(user1);
        userService.removeById(1l);

        List<User> userListFromTestClass=userService.getAllUsers();
        assertEquals(users,userListFromTestClass);
    }
}
