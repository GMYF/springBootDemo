package com.light.springboot.mappers;

import com.light.springboot.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    List<Map> findById(Integer id);
    boolean addUser(User user);
    boolean syncPassWord(Map users);
}
