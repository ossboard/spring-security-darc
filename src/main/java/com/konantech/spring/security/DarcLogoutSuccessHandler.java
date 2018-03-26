package com.konantech.spring.security;

import _1._0._0._127.darc_wsdl.DarcPortType;
import com.konantech.spring.darcLib.model.AuthSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DarcLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private DarcPortType portType;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication != null) {
            System.out.println(authentication.getName());
        }
        AuthSessionInfo darcSessionInfo = (AuthSessionInfo) authentication.getDetails();
        portType.logOut(darcSessionInfo.getSessionId());
    }
}
