package com.learn.idea_ws_demo;

import com.learn.idea_ws_demo.entity.People;
import com.learn.idea_ws_demo.mapper.UserMapper;
import com.learn.idea_ws_demo.pojo.User;
import com.learn.idea_ws_demo.repository.PeopleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdeaWsDemo01ApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PeopleRepository peopleRepository;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();

        System.out.println(connection);

        connection.close();
    }

    @Test
    public void test_01(){
        People people = peopleRepository.findByUsernameAndPassword("张三", "123");
        System.out.println(people);
    }

    @Test
    public void test_02(){
        User user = userMapper.selUserById(1);
        System.out.println(user);
    }
}
