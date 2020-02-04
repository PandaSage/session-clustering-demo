package com.aeexe.sessionclustringdemobackend.repository;

import com.aeexe.sessionclustringdemobackend.domain.TOKEN;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<TOKEN, String> {
    Optional<TOKEN> findBySeries(final String series);
}
