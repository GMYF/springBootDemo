package com.light.springboot.dao;

import com.light.springboot.domain.Token;
import com.light.springboot.domain.user.UserToken;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Token record);

    int insert(UserToken record);

    Token selectByPrimaryKey(Integer id);

    List<Token> selectAll();

    int updateByPrimaryKey(Token record);

    UserToken getUserByToken(String token);

}