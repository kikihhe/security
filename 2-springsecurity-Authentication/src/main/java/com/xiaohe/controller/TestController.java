package com.xiaohe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-03 09:50
 */
@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello security";
    }
}
