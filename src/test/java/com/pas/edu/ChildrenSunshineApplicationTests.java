package com.pas.edu;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pas.edu.dao.UserDao;
import com.pas.edu.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChildrenSunshineApplicationTests {
    @Autowired
    UserDao userDao;

    @Test
    public void queryUser() {
        Page<User> page = PageHelper.startPage(0, 3);
        userDao.getUserAll();
        System.out.println("===>" + page.getResult());
    }

    @Test
    public void contextLoads() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String mHashStr = "test_door_1" + df.format(new Date());
        System.out.println(passwordHash(mHashStr));
    }

    public static int passwordHash(String str) {
        int h = 0;
        int off = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            h = 31 * h + str.charAt(off++);
        }
        return h;
    }

}
