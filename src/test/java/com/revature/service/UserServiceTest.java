package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Test
    public void LoginTest() throws SQLException, FailedLoginException {
        UserDao mockDao = mock(UserDao.class);
        UserService userService =new UserService(mockDao);
        User fakeUser=new User(1,"jenob","password321","jenob","employee");
        when(mockDao.getUsernameAndPassword("jenob","password321")).thenReturn(fakeUser);
        User actual=userService.login("jenob","password321");
        User expected=fakeUser;
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void InvalidLoginTest(){
        UserDao mockDao=mock(UserDao.class);
        UserService userService=new UserService(mockDao);
        Assertions.assertThrows(FailedLoginException.class, () ->{
            userService.login("sss","ddd");
        });
    }

}
