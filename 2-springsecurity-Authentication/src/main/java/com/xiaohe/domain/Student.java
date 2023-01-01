package com.xiaohe.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-03 08:54
 */
@Data
public class Student implements UserDetails {
    private String id;
    private String name;
    private String password;
    private List<Role> roles;



    // 将用户权限交给security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> list = getRoles();
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        list.stream().map((role -> {

            roles.add(new SimpleGrantedAuthority(role.getName()));
            return role;
        }));
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
