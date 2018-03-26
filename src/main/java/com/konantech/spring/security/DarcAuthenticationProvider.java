package com.konantech.spring.security;

import com.konantech.spring.darcLib.model.AuthSessionInfo;
import com.konantech.spring.darcLib.model.DarcLogIn;
import com.konantech.spring.darcLib.model.DarcSpecialUserAuthorityList;
import com.konantech.spring.darcLib.model.DarcUserAuthorityList;
import com.konantech.spring.darcLib.service.DarcPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DarcAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private DarcPort darcPort;

    @Value("${darc.clientType}")
    private int clientType;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        try {
            DarcLogIn darcLogIn = darcPort.logIn(clientType, username, password);
            if (darcLogIn.getError() == null) {
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // temp
                int sessionId = darcLogIn.getLogin().getSessionid();
//                DarcUserAuthorityList darcUserAuthorityList = darcPort.getUserAuthorityList(sessionId);
//                if(darcUserAuthorityList.getGetuserauthoritylist().getAuthority() != null) {
//                    for (DarcUserAuthorityList.Authority authority : darcUserAuthorityList.getGetuserauthoritylist().getAuthority()) {
//                        authorities.add(new SimpleGrantedAuthority(authority.getAuthorityname()));
//                    }
//                }
                DarcSpecialUserAuthorityList darcSpecialUserAuthorityList = darcPort.getSpecialUserAuthorityList(sessionId, "kbs");
                if(darcSpecialUserAuthorityList.getGetspecialuserauthoritylist().getAuthority() != null) {
                    for (DarcSpecialUserAuthorityList.Authority authority : darcSpecialUserAuthorityList.getGetspecialuserauthoritylist().getAuthority()) {
                        authorities.add(new SimpleGrantedAuthority(authority.getAuthorityname()));
                    }
                }
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, authorities);
                usernamePasswordAuthenticationToken.setDetails(new AuthSessionInfo(sessionId));
                return usernamePasswordAuthenticationToken;
            } else {
                throw new BadCredentialsException(darcLogIn.getError().getErrormsg());
            }
        } catch (Exception ignore) {
            throw new BadCredentialsException("External system authentication failed(2)");
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
