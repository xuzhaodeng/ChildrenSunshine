package com.pas.edu;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pas.edu.dao.OrganDao;
import com.pas.edu.dao.UserDao;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.User;
import com.pas.edu.service.OrganService;
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

    @Autowired
    OrganDao organDao;

    @Autowired
    OrganService organService;

    @Test
    public void queryUser() {
        Page<User> page = PageHelper.startPage(0, 3);
        userDao.getUserAll();
        System.out.println("===>" + page.getResult());
    }

    @Test
    public void queryUserId() {
        User user = userDao.getUserById(2);
        System.out.println("===>" + user);
    }

    @Test
    public void update() {
      userDao.updatePwd(1,"111112");
    }

    @Test
    public void queryOrgan() {
//        List<Organ> list = organDao.getChildOrganList(3);
//        Organ organ= organDao.getOrgan(3);
        Organ organ = null;
        try {
            organ = organService.getOrganDetail(3);
            System.out.println("===>" + organ);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
