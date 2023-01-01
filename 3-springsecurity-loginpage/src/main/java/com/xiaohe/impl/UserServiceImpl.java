package com.xiaohe.impl;

import cn.hutool.core.util.ObjectUtil;
import com.xiaohe.domain.entity.User;
import com.xiaohe.mapper.UserMapper;
import com.xiaohe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-17 23:14
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService, UserDetailsPasswordService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 从数据库中查真正的用户名/密码，交给security对比。
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.getUserByPhone(s);
        if(ObjectUtil.isEmpty(user)) {
            throw new UsernameNotFoundException("用户名/密码错误!");
        }
        return user;
    }

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String s) {
        return null;
    }
}
