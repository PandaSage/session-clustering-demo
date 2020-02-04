package com.aeexe.sessionclustringdemobackend.serviceImpl;

import com.aeexe.sessionclustringdemobackend.domain.USER;
import com.aeexe.sessionclustringdemobackend.model.MyAuthenticaion;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthProviderImpl implements AuthenticationProvider {
    private final AuthorizationServiceImpl authorizationService;

    private Authentication authenticate(String id, String password) throws AuthenticationException {
        USER user = authorizationService.login(id, password);
        if (user == null) return null;
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        String role = "user";
        grantedAuthorityList.add(new SimpleGrantedAuthority(role));
        return new MyAuthenticaion(id, password, grantedAuthorityList, user);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(id, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
