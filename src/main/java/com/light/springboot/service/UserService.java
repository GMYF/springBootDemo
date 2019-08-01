package com.light.springboot.service;

import com.light.springboot.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public List<Map> findById(Integer id){
        return userMapper.findById(id);
    }
}
