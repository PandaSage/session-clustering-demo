package com.aeexe.sessionclustringdemobackend.serviceImpl;

import com.aeexe.sessionclustringdemobackend.domain.USER;
import com.aeexe.sessionclustringdemobackend.model.UserDetails;
import com.aeexe.sessionclustringdemobackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    final private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<USER> user = userRepository.findById(s);
        if (!user.isPresent()) {
            log.info("loginFail");
            throw new UsernameNotFoundException("login Fail");
        }
        return new UserDetails(user.get());
    }
}
