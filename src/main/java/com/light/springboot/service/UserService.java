package com.light.springboot.service;

import com.light.springboot.domain.user.User;
import com.light.springboot.domain.user.UserToken;
import com.light.springboot.mappers.UserMapper;
import com.light.springboot.util.date.DateUtil;
import com.light.springboot.util.md5.MD5Utils;
import com.light.springboot.util.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User findById(Integer id){
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
    public User login(String userName,String password){
        User user = userMapper.login(userName,MD5Utils.md5Encrypt32Lower(password));
        if (user!=null){
            //  生成token，规则以当前时间和用户名生成32位hash码
            long date = DateUtil.getTimeLocal();
            String token = TokenUtil.getToken(date,userName);
            UserToken userToken = new UserToken();
            userToken.setUserId(user.getId());
            userToken.setToken(token);
            userMapper.saveToken(userToken);
            user.setToken(token);
            return user;
        }
        return null;
    }

    public UserToken getUserByToken(String token) {
      return userMapper.getUserByToken(token);
    }
}
