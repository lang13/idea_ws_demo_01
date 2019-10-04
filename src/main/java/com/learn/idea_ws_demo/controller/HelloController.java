package com.learn.idea_ws_demo.controller;

import com.learn.idea_ws_demo.mapper.UserMapper;
import com.learn.idea_ws_demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
public class HelloController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return userMapper.selUserById(id);
    }

    @RequestMapping("/user")
    public User insUser(User user){
        userMapper.insUser(user);
        return user;
    }
}
