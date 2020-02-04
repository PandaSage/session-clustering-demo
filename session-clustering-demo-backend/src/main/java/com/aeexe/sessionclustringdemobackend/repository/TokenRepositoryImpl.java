package com.aeexe.sessionclustringdemobackend.repository;

import com.aeexe.sessionclustringdemobackend.domain.TOKEN;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TokenRepositoryImpl implements PersistentTokenRepository {
    private TokenRepository tokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        log.info("create token!!!!!!!!!!!!!!");
        TOKEN newToken = new TOKEN();
        newToken.setUsername(persistentRememberMeToken.getUsername());
        newToken.setToken(persistentRememberMeToken.getTokenValue());
        newToken.setSeries(persistentRememberMeToken.getSeries());
        newToken.setLastUsed(persistentRememberMeToken.getDate());
        log.info(newToken.toString());
        tokenRepository.save(newToken);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        log.info("updateToken");
        Optional<TOKEN> token = tokenRepository.findById(series);
        if (token.isPresent()) {
            TOKEN updateToken = token.get();
            updateToken.setToken(tokenValue);
            updateToken.setLastUsed(lastUsed);
            updateToken.setSeries(series);
            tokenRepository.save(updateToken);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        log.info("persistentToken");
        Optional<TOKEN> token = tokenRepository.findBySeries(series);
        if (token.isPresent()) {
            PersistentRememberMeToken persistentRememberMeToken =
                    new PersistentRememberMeToken(token.get().getUsername(), series, token.get().getToken(), token.get().getLastUsed());
            return persistentRememberMeToken;
        } else {
            log.info("persistentToken failed");
        }
        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        log.info("removeToken");
        Optional<TOKEN> token = tokenRepository.findById(username);
        log.info(token.get().toString());
        if (token.isPresent()) tokenRepository.delete(token.get());
    }
}
