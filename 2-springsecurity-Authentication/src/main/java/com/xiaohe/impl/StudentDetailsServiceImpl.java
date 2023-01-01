package com.xiaohe.impl;

import com.xiaohe.domain.Student;
import com.xiaohe.mapper.StudentMapper;
import com.xiaohe.service.StudentService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-03 09:25
 */
@Service
public class StudentDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private StudentService studentService;

    // 根据id查询用户
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(Strings.isEmpty(s) || s.equals("")) {
            throw new UsernameNotFoundException("学号无法为空");
        }
        Student student = studentService.getOneById(s);
        if (Objects.isNull(student)) {
            throw new UsernameNotFoundException("账号/密码错误!");
        }
        return new Student();
    }
}
