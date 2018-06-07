package com.mmall;

import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author jiangxl
 * @description
 * @date 2018-05-29 17:36
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DBTest {

    private Connection conn = null;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void conn() {
        System.out.println(username);
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);



    }
}
