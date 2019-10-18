package com.light.springboot.mappers;

import com.light.springboot.domain.user.UserToken;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lzz
 */
@Component
public interface TokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserToken record);

    UserToken selectByPrimaryKey(Integer id);

    List<UserToken> selectAll();

    int updateByPrimaryKey(UserToken record);

    UserToken  getUserByToken(String token);
}