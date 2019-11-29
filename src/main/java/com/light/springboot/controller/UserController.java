package com.light.springboot.controller;


import com.light.springboot.domain.user.User;
import com.light.springboot.exception.CustomException;
import com.light.springboot.service.UserService;
import com.light.springboot.util.info.CodeMsg;
import com.light.springboot.util.response.ResponseResult;
import com.light.springboot.util.response.ResponseStatus;
import com.light.springboot.util.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public ResponseResult<User> getUserDetail(@PathVariable int id) {
        if (id <= 0) {
            throw new CustomException(CodeMsg.USER_NO_ID);
        }
        System.out.println(1 / 0);
        User user = userService.findById(id);
        return ResponseResult.success(user);
    }

    @RequestMapping(value = "/test")
    public void test() {
        System.out.println(1 / 0);
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestParam Map map, HttpServletRequest request, HttpServletResponse response) {
        String username = StringUtils.safe2String(map.get("username"));
        String password = StringUtils.safe2String(map.get("password"));
        User user = userService.login(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(1000 * 30);
            session.setAttribute("token", user.getToken());
        }
        return new ResponseResult();
    }

    @PostMapping("/add")
    public ResponseResult addUser(@RequestParam Map map) {
        String username = StringUtils.safe2String(map.get("username"));
        User user = new User();
        user.setName(username);
        user.setPhone("");
        userService.addUser(user);
        return null;
    }

    @PostMapping("/file")
    public ResponseResult addFile(@RequestParam("file") MultipartFile file) {
        //file是form-data中二进制字段对应的name
        System.out.println(file);
        file.getInputStream();
        return new ResponseResult();
    }
}
