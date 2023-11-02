package com.example.memelli.prod.crud.Components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.example.memelli.prod.crud.entities.User;
import com.example.memelli.prod.crud.repositories.UserRepository;

@Component
public class JwtTokenEnhancer implements TokenEnhancer{

    @Autowired
    private UserRepository userRepository;


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken arg0, OAuth2Authentication arg1) {
        User user = userRepository.findByEmail(arg1.getName());
        
        Map<String, Object> map = new HashMap<>();      
        map.put("userName", user.getEmail());
        map.put("userId", user.getId());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) arg0;
        token.setAdditionalInformation(map);
        return arg0;
    }
    
}
