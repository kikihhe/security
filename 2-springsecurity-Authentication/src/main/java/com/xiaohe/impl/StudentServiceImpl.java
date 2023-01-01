package com.xiaohe.impl;

import com.xiaohe.domain.Role;
import com.xiaohe.domain.Student;
import com.xiaohe.mapper.StudentMapper;
import com.xiaohe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-03 09:38
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student getOneById(String id) {
        Student student = studentMapper.selectStudentById(id);
        List<Role> roles = studentMapper.selectRolesByStudentId(id);
        student.setRoles(roles);
        return student;
    }
}
