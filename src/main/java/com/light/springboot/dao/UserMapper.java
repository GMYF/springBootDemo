package com.light.springboot.dao;

import java.util.List;

import com.light.springboot.domain.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(@Param("host") String host, @Param("user") String user);

    int insert(User record);

    User selectByPrimaryKey(@Param("host") String host, @Param("user") String user);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User findById(@Param("id") int id);

    User login(@Param("userName")String name,@Param("passWord") String word);

}