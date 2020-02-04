package com.aeexe.sessionclustringdemobackend.serviceImpl;

import com.aeexe.sessionclustringdemobackend.domain.USER;
import com.aeexe.sessionclustringdemobackend.model.MyAuthenticaion;
import com.aeexe.sessionclustringdemobackend.repository.UserRepository;
import com.aeexe.sessionclustringdemobackend.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserRepository userRepository;

    @Override
    public USER login(final String id, final String pw) {
        Optional<USER> user = userRepository.findById(id);
        log.info("id : %s pw : %s", id, pw);
        if (!user.isPresent()) {
            log.info("no user");
            return null;
        } else {
            if (user.get().getPw().equals(pw)) return user.get();
            else {
                log.info("not match pw");
                return null;
            }
        }
    }

    @Override
    public USER getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof MyAuthenticaion)
            return ((MyAuthenticaion) authentication).getUser();
        return null;
    }

    @Override
    public void setCurrentUser(USER user) {
        ((MyAuthenticaion)
                SecurityContextHolder.getContext().getAuthentication()).setUser(user);
    }
}
