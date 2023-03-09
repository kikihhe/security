package com.xiaohe.oauth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

// 基于内存的认证服务器
///**
// * 认证授权服务器
// */
//@Configuration
//@EnableAuthorizationServer // 指定当前应用为授权服务器
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    private final UserDetailsService userDetailsService;
//
//    @Autowired
//    public AuthorizationServerConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public  PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // 授权码模式两步:
//    //  1. 请求用户授权
//    //      完整路径: http://localhost:8080/oauth/authorize?client_id=clientId&response_type=code&redirect_uri=http://www.baidu.com
//    //  获得授权码: 3hjxxt
//    //  2. 授权后根据获取的授权码获取令牌.
//    //      完整路径: POST请求: http://clientId:clientSecret@localhost:8080/oauth/token
//    //                 请求体: grant_type=authorization_code
//    //                        code=3hjxxt
//    //                        redirect_uri=http://www.baidu.com
//    // 获取 access_token、expires_in(有效期,默认12h)、scope、token_type
//
//    // 支持刷新令牌
//    // authorizationGrantTypes中加上refresh_token
//    // 请求令牌后获取令牌和refresh_token
//    // 刷新token的完整路径: http://clientId:clientSecret@localhost:8080/oauth/token
//    //             请求体: grant_type=refresh_token
//    //                    refresh_token=xxxxxxxx
//    //                    client_id=clientId
//    // 拿到刷新后的令牌
//    // 注意: 刷新token需要先指定UserDetailsService. 在config(endpoints)中指定
//    /**
//     *  配置授权服务器可以为哪些客户端授权
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        // 配置一个基于内存的授权服务器
//        clients.inMemory()
//                .withClient("clientId")
//                .secret(passwordEncoder().encode("clientSecret")) // 客户端密钥必须使用PasswordEncoder加密
//                .redirectUris("http://www.baidu.com")
//                .authorizedGrantTypes("authorization_code", "refresh_token") // 授权码模式,并刷新token
//                .scopes("read:user"); // 令牌允许获取的资源权限.
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.userDetailsService(userDetailsService);
//    }
//
//}
