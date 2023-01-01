package com.xiaohe.domain.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-17 12:24
 */
@Data
public class User implements UserDetails {
    /**
     * 用户手机号,主键
     */
    private String phone;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户昵称
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户住址
     */
    private String address;
    /**
     * 账号是否可用
     */
    private Integer enabled;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled == 1;
    }

    @Override
    public boolean isEnabled() {
        return enabled == 1;
    }
}
