package com.light.springboot.service;

import com.light.springboot.domain.user.User;
import com.light.springboot.mappers.UserMapper;
import com.light.springboot.util.md5.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public List<Map> findById(Integer id){
        return userMapper.findById(id);
    }

    public boolean addUser(User user){
       userMapper.addUser(user);
       Map users = new HashMap();
       // 默认的密码
       users.put("userId",user.getId());
       users.put("password", MD5Utils.genInitPassWord());
       userMapper.syncPassWord(users);
       return true;
    };

    /**
     * 请求登录
     * @return
     */
    public Boolean login(String userName,String password){

        User user = userMapper.login(userName,MD5Utils.md5Encrypt32Lower(password));
        if (user!=null){
            return true;
        }
        return false;
    }
}
