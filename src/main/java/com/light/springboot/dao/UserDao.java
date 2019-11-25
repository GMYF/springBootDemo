package com.light.springboot.dao;

import com.light.springboot.domain.user.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    int deleteByPrimaryKey(@Param("host") String host, @Param("user") String user);

    int insert(User record);

    /**
     * 用户登录
     * @param name
     * @param word
     */
    User login(@Param("userName")String name,@Param("passWord") String word);
    User findById(@Param("id") int id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}