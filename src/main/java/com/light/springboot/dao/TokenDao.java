package com.light.springboot.dao;

import com.light.springboot.domain.user.UserToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzz
 */
@Repository
public interface TokenDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserToken record);

    UserToken selectByPrimaryKey(Integer id);

    List<UserToken> selectAll();

    int updateByPrimaryKey(UserToken record);

    UserToken  getUserByToken(String token);
}