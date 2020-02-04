package com.aeexe.sessionclustringdemobackend.model;

import com.aeexe.sessionclustringdemobackend.domain.USER;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MyAuthenticaion extends UsernamePasswordAuthenticationToken implements Serializable {
    private static final long serialVersionUID = 1L;

    USER user;

    public MyAuthenticaion(String id, String password, List<GrantedAuthority> grantedAuthorityList, USER user) {
        super(id, password, grantedAuthorityList);
        this.user = user;
    }
}
