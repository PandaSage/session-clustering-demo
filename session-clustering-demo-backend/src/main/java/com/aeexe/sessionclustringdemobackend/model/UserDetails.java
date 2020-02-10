package com.aeexe.sessionclustringdemobackend.model;

import com.aeexe.sessionclustringdemobackend.domain.USER;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;

public class UserDetails extends User implements Serializable {
    public UserDetails(USER user) {
        super(user.getId(), user.getPw(), AuthorityUtils.createAuthorityList("BACKEND"));
    }
}
