package dao;


import com.sun.org.apache.bcel.internal.generic.ATHROW;
import entity.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    public final String FILENAME="TempUser.txt";
    UserDaoImpl userDaoImpl;

    @Before
    public void setUp() throws IOException{
        userDaoImpl=new UserDaoImpl(FILENAME);
        PrintWriter printWriter=new PrintWriter(FILENAME);
        printWriter.close();
    }

    @Test
    public void shouldReturnNullAllUserWhileFileEmpty() throws IOException {
        PrintWriter printWriter=new PrintWriter(FILENAME);
        printWriter.close();
        List<User> userList=userDaoImpl.getAllUsers();
        assertEquals(0,userList.size());
    }

    @Test
    public void shouldSaveListWithTwo() throws IOException{
        userDaoImpl.clearFile(FILENAME);
        List <User> usersToSave=new ArrayList<>();
        usersToSave.add(new User(1L,"admin","admin"));
        usersToSave.add(new User(2L,"edyta","edyta"));
        userDaoImpl.saveUsers(usersToSave);
        assertEquals(usersToSave.size(),userDaoImpl.getAllUsers().size());
    }

    @Test
    public void shouldSaveEmptyList() throws IOException{
        int beforeAdd=userDaoImpl.getAllUsers().size();
        List <User> emptyList= new ArrayList<>();
        userDaoImpl.saveUsers(emptyList);
        int afterAdd=userDaoImpl.getAllUsers().size();
        assertEquals(beforeAdd,afterAdd);
    }

    @Test
    public void shouldSaveOneUser() throws IOException{
        int beforeAdd=userDaoImpl.getAllUsers().size();
        userDaoImpl.saveUser(new User(3L,"damian","damian"));
        int afeterAdd=userDaoImpl.getAllUsers().size();
        assertEquals(beforeAdd+1,afeterAdd);
    }

    @Test
    public void shouldReturnTheSameUserGetByLogin() throws IOException{
        User user=new User(4L,"ania","ania");
        userDaoImpl.saveUser(user);
        User user1=userDaoImpl.getUserByLogin("ania");
        assertEquals(user,user1);
    }

    @Test
    public void shouldReturnNullGetUserByLogin() throws IOException{
        User user=userDaoImpl.getUserByLogin("niema");
        assertNull(user);
    }

    @Test
    public void shouldReturnTheSameUserGetById() throws IOException{
        User user=new User(5L,"maciek","maciek");
        userDaoImpl.saveUser(user);
        User user1=userDaoImpl.getUserById(5L);
        assertEquals(user,user1);
    }

    @Test
    public void shouldRemoveExistedByLogin() throws IOException{
        int sizeBefore=userDaoImpl.getAllUsers().size();
        User user = new User(6L,"kasia","kasia");
        userDaoImpl.saveUser(user);
        userDaoImpl.removeUserByLogin("kasia");
        int sizeAfter=userDaoImpl.getAllUsers().size();
        assertEquals(sizeBefore,sizeAfter);
    }

    @Test
    public void shouldNotRemoveNonExistingByLogin() throws IOException{
        int sizeBefore=userDaoImpl.getAllUsers().size();
        userDaoImpl.removeUserByLogin("niema");
        int sizeAfter=userDaoImpl.getAllUsers().size();
        assertEquals(sizeBefore,sizeAfter);
    }

    @Test
    public void shouldRemoveExistedById() throws IOException{
        int sizeBefore=userDaoImpl.getAllUsers().size();
        User user = new User(7L,"paulina","paulina");
        userDaoImpl.saveUser(user);
        userDaoImpl.removeUserById(7L);
        int sizeAfter=userDaoImpl.getAllUsers().size();
        assertEquals(sizeBefore,sizeAfter);
    }

    @Test
    public void shouldNotRemoveNonExistingById() throws IOException{
        int sizeBefore=userDaoImpl.getAllUsers().size();
        userDaoImpl.removeUserById(10L);
        int sizeAfter=userDaoImpl.getAllUsers().size();
        assertEquals(sizeBefore,sizeAfter);
    }
}
