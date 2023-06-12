package com.carlojr.simpleoauth2.config;

import com.carlojr.simpleoauth2.user.EnhanceUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        EnhanceUser enhanceUser = (EnhanceUser)authentication.getPrincipal();
        Map<String, Object> addInfo = new HashMap<String, Object>();

        addInfo.put("name", enhanceUser.getUser().getDbName());
        addInfo.put("user_name", enhanceUser.getUser().getUsername());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
        return accessToken;
    }
}

