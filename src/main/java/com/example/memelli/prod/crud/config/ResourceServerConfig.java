package com.example.memelli.prod.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer // habilita o servidor de recursos
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{ //configuração para obter as tabelas de acordo com o token e a autorização

    @Autowired
    private JwtTokenStore tokenStore;

    private static final String[] PUBLIC = { "/oauth/token" };

    private static final String[] OPERATOR_OR_ADMIN = { "/projects/**", "/tasks/**" };

    private static final String[] ADMIN = { "/users/**", "/projects/**", "/tasks/**" }; //tirar projects e tasks daqui



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
            .antMatchers(PUBLIC).permitAll()
            .antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll() //  , OPERATOR_OR_ADMIN, PUBLIC
            .antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR", "ADMIN")
            .antMatchers(ADMIN).hasRole("ADMIN")
            .anyRequest().authenticated();
    }    
}
