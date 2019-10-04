package com.learn.idea_ws_demo.entity;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.InternetHeaders;

import javax.persistence.*;

@Entity         //告诉jpa这是一个实体类(和数据表映射的类)
@Table(name = "people")          /**用来指定和哪个数据表对应,如果没有设置name属性,则表的名称为类的小写名*/
public class People {

    @Id         //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)/**设置为自增*/
    private Integer id;

    @Column     /**这是和数据表对应的一个列  可配置name,length等属性  */
    private String username;
    @Column
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
