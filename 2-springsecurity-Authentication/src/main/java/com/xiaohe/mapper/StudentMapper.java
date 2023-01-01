package com.xiaohe.mapper;

import com.xiaohe.domain.Role;
import com.xiaohe.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-03 08:52
 */
@Mapper
public interface StudentMapper {
    public Student selectStudentById(@Param("id") String id);

    public List<Role> selectRolesByStudentId(@Param("id") String id);

}
