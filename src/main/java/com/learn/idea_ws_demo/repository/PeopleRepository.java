package com.learn.idea_ws_demo.repository;

import com.learn.idea_ws_demo.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 继承JpaRepository,用来操作数据库
 * JpaRepository的泛型,第一个是数据表的类对象,第二个是类对象的主键类型
 * @author Administrator
 */
public interface PeopleRepository extends JpaRepository<People,Integer> {

    /**
     * 按照账户密码查询
     * @param username
     * @param password
     * @return
     */
    public People findByUsernameAndPassword(String username, String password);
}
