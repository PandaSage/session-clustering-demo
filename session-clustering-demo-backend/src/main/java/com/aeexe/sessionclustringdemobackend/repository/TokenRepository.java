package com.aeexe.sessionclustringdemobackend.repository;

import com.aeexe.sessionclustringdemobackend.domain.TOKEN;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<TOKEN, String> {
    Optional<TOKEN> findBySeries(final String series);
}
