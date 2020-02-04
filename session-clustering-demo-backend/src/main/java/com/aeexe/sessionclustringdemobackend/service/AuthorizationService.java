package com.aeexe.sessionclustringdemobackend.service;

import com.aeexe.sessionclustringdemobackend.domain.USER;

public interface AuthorizationService {
    USER login(final String id, final String pw);

    USER getCurrentUser();

    void setCurrentUser(final USER user);
}
