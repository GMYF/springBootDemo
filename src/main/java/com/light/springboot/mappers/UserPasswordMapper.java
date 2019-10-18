package com.light.springboot.mappers;

import com.light.springboot.domain.user.UserPassword;
import java.util.List;

public interface UserPasswordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPassword record);

    UserPassword selectByPrimaryKey(Integer id);

    List<UserPassword> selectAll();

    int updateByPrimaryKey(UserPassword record);
}