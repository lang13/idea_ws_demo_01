package com.learn.idea_ws_demo.mapper;

import com.learn.idea_ws_demo.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * @author Administrator
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    public User selUserById(int id);

    @Delete("delete from user where id = #{id}")
    public int delUserById(int id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into user values(null,#{username},#{password})")
    public int insUser(User user);

    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    public int updUserById(User user);

    public int updPassWord(User user);
}



