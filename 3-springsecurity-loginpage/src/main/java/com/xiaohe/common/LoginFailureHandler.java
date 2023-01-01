package com.xiaohe.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录失败处理类
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        // 将json返回给前端: 登陆失败,code为0，message使用异常里的信息.
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "登录失败");
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
