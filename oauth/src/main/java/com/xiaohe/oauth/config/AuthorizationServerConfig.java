package com.xiaohe.oauth.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 认证授权服务器
 */
@Configuration
@EnableAuthorizationServer // 指定当前应用为授权服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // 授权码模式两步: 1. 请求用户是否授权   2. 授权后根据获取的授权码获取令牌.
    // 配置授权服务器可以为哪些客户端授权
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 配置一个基于内存的授权服务器
        clients.inMemory()
                .withClient("clientId")
                .secret("clientSecret")
                .redirectUris("www.baidu.com")
                .authorizedGrantTypes("authorization_code") // 授权码模式
                .scopes("read:user"); // 令牌允许获取的资源权限.

    }
}
