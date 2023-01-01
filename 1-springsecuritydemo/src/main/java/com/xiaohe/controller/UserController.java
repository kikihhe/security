package com.xiaohe.controller;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-10-29 21:30
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @TableField()
int age;
    @GetMapping("/hello")
    public String hello() {
        log.info("访问hello路径");
        return "hello";
    }
}
