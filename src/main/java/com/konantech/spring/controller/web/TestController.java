package com.konantech.spring.controller.web;

import com.konantech.spring.darcLib.model.AuthSessionInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class TestController {

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo(Principal principal, HttpSession session, HttpServletRequest request, Authentication authentication) throws Exception {

        System.out.println(principal.getName());
        System.out.println(authentication.getName());

        AuthSessionInfo darcSessionInfo = (AuthSessionInfo) authentication.getDetails();
        System.out.println(darcSessionInfo);

        System.out.println(session.getId());

        boolean role = request.isUserInRole("ROLE_USER");
        System.out.println(role);
        System.out.println();
        return "demo";
    }

}