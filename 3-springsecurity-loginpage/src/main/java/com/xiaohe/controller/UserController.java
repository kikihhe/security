package com.xiaohe.controller;


import com.xiaohe.service.UserService;

import com.xiaohe.utils.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-03 10:12
 */
@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/hello")
    public Result hello() {
        return Result.success("访问成功");
    }
}
