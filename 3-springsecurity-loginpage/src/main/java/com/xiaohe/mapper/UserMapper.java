package com.xiaohe.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-17 12:44
 */
@Mapper
@TableName("user")
public interface UserMapper {
    public User getUserByPhone(@RequestBody String phone);
}
