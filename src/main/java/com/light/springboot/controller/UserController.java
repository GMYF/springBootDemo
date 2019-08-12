package com.light.springboot.controller;


import com.light.springboot.domain.user.User;
import com.light.springboot.service.UserService;
import com.light.springboot.util.response.ResponseResult;
import com.light.springboot.util.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author add00
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/detail/{id}")
    public ResponseResult getUserDetail(@PathVariable int id){
        Map<String,Object> data = new HashMap<>();
        List<Map> user = userService.findById(id);
        if(user!=null){
            data.put("user",user);
        }
        ResponseResult responseResult = new ResponseResult(data);
        return responseResult;
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestParam Map map){
        String username = StringUtils.safe2String(map.get("username"));
        ResponseResult responseResult = new ResponseResult();
        return responseResult;
    }

    @PostMapping("/add")
    public ResponseResult addUser(@RequestParam Map map){
        String username = StringUtils.safe2String(map.get("username"));
        User user = new User();
        user.setName(username);
        user.setPhone("");
        userService.addUser(user);
        return  null;
    }
}
