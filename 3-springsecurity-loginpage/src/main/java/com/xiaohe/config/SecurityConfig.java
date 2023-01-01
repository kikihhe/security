package com.xiaohe.config;



import com.xiaohe.common.LoginFailureHandler;
import com.xiaohe.common.LoginSuccessHandler;
import com.xiaohe.common.MyLogoutSuccessHandler;

import com.xiaohe.filter.LoginFilter;
import com.xiaohe.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * security的配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private String usernameParameter = "phone";

    private String passwordParameter = "password";

    /**
     * 注入UserDetailsService
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    /**
     * 使用自定义AuthenticationManager
     * @return
     * @throws Exception
     */
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 让AuthenticationManager使用自定义UserDetailsService和自定义BCryptPasswordEncoder
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * 注入密码加密类
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义登录过滤认证器
     * @return
     */
    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setUsernameParameter(usernameParameter);
        loginFilter.setPasswordParameter(passwordParameter);
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        return loginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf防护
        http.csrf().disable();

        // 关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 指定放行资源
        String[] permit = {
                "/view/login.html",
                "/common/getCode",
                "/common/upload",
                "/user/login",
                "/user/logout",
                "/user/register"
        };
        http.authorizeRequests()
                        .mvcMatchers(permit).permitAll()
                        .anyRequest().authenticated();
        // 指定登录页面、登录路径、成功失败处理器
        http.formLogin()
                .loginPage("/view/login.html")
                .loginProcessingUrl("/user/login")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler());
        // 指定退出登录成功后的处理,返回json...
        http.logout().
                logoutSuccessHandler(new MyLogoutSuccessHandler());


        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);


    }
}
