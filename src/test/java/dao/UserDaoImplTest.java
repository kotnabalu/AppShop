package dao;

import api.UserDao;
import entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class UserDaoImplTest {
   UserDao userDao;

    @Before
    public void setUp(){
        try{
            userDao=new UserDaoImpl();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void isUserExistTest(){
        User user =new User("Wakacje","Wakacje2020");
        userDao.saveUser(user);
       boolean isTrue= userDao.isUserExist("Wakacje");
       assertTrue(isTrue);
    }

    @Test
    public void DontSaveUserWhenAlreadyExist(){
        User user=new User("Wakacje","InneHaslo10");
        int sizeBefore=userDao.getAllUsers().size();
        userDao.saveUser(user);
        int sizeAfter=userDao.getAllUsers().size();
        assertEquals(sizeBefore,sizeAfter);

    }




    @Test
    public void getUserByLoginTest(){
        User user=new User("kotnabalu","Damianek12");
        User userReturned=userDao.getUserByLogin("kotnabalu");
        assertEquals(user,userReturned);
    }



    @Test
    public void removeUserByLoginTest(){
        User user=new User("Mariola","Mariola020");
        userDao.saveUser(user);
        int sizeBefore=userDao.getAllUsers().size();
        String login="Mariola";
        userDao.removeUserByLogin(login);
        int afterSize=userDao.getAllUsers().size();
        assertNotEquals(sizeBefore,afterSize);
    }


}
