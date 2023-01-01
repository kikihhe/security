package com.xiaohe.filter;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-18 10:00
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 如果不是post请求，直接报错
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // 如果传来的数据是queryString类型，使用默认处理
        if(!request.getContentType().equalsIgnoreCase("application/json;charset=UTF-8")) {
            return super.attemptAuthentication(request, response);
        }
        // 如果传来的数据是json
        Map<String, String> map = new HashMap<>();
        try {
            log.debug(JSON.toJSONString(request.getInputStream()));
            map = JSON.parseObject(request.getInputStream(), Map.class);
            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取用户输入的手机号、密码
        String phone = map.get(getUsernameParameter());
        String password = map.get(getPasswordParameter());
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(phone, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
