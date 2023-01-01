package com.xiaohe.common;

import com.alibaba.fastjson2.JSON;

import com.xiaohe.utils.JWTUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功后的处理类
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");

        // 登陆成功，生成token,将其存入redis,并返回给前端
        String json = JSON.toJSONString(authentication);
        HashMap hashMap = JSON.parseObject(json, HashMap.class);
        String token = JWTUtils.generateToken(hashMap);



        //TODO 存入redis
        // key:token, value: json
        // 以后取出的是json，转为authentication对象即可。



        // 将json返回给前端
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("message", "登录成功!");
        map.put("data", token);
        httpServletResponse.getWriter().write(JSON.toJSONString(map));

    }
}
