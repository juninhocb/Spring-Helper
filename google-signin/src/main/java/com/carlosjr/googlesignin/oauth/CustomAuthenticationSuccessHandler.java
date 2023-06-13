package com.carlosjr.googlesignin.oauth;

import com.carlosjr.googlesignin.user.User;
import com.carlosjr.googlesignin.user.UserRepository;
import com.carlosjr.googlesignin.user.UserService;
import com.carlosjr.googlesignin.user.enums.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = new User();
        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        user.setName(String.valueOf(oauthUser.getAttributes().get("given_name")));
        user.setProvider(Provider.GOOGLE);
        user.setPassword(String.valueOf(oauthUser.getAttributes().get("nonce")));
        user.setUsername(String.valueOf(oauthUser.getAttributes().get("email")));
        System.out.println(user.toString());
        if (userRepository.findByUsername(user.getUsername()) == null)
            userRepository.save(user);
        response.sendRedirect("/index");
    }
}
