package com.xiaohe;

import com.xiaohe.domain.Student;
import com.xiaohe.mapper.StudentMapper;
import com.xiaohe.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void contextLoads() {
        System.out.println(studentMapper.selectStudentById("01"));
    }
    @Autowired
    private StudentService studentService;
    @Test
    void testStudentService() {
        Student student = studentService.getOneById("12103990605");
        System.out.println(student);
    }

    @Test
    public void passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }

}
