package com.light.springboot.mappers;

import com.light.springboot.domain.user.User;
import com.light.springboot.domain.user.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Mapper
public interface UserMapper {
    User findById(Integer id);
    boolean addUser(User user);
    boolean syncPassWord(Map map);

    /**
     *
     * @param userName
     * @param passWord
     * @return
     */
    User login(@Param("userName") String userName,@Param("passWord") String passWord);

    /**
     * 保存token
     * @param token
     * @param id
     */
    void saveToken(String token, Integer id);

    UserToken getToken(User user);
}
