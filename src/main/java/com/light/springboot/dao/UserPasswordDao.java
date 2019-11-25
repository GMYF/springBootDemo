package com.light.springboot.dao;

import com.light.springboot.domain.user.UserPassword;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserPasswordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPassword record);

    UserPassword selectByPrimaryKey(Integer id);

    List<UserPassword> selectAll();

    int updateByPrimaryKey(UserPassword record);
}