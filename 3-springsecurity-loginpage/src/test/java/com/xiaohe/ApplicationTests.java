package com.xiaohe;

import com.alibaba.fastjson2.JSON;
import com.xiaohe.domain.entity.User;
import com.xiaohe.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Test
    void testStudentMapper() {
//        System.out.println(passwordEncoder.encode("123456"));
        User user = userMapper.getUserByPhone("16623432421");
        System.out.println(user);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisTemplate() {
        User user = userMapper.getUserByPhone("16623432421");
        String json = JSON.toJSONString(user);
        stringRedisTemplate.opsForValue().set(user.getPhone(), json, 30, TimeUnit.MINUTES);

    }

}
