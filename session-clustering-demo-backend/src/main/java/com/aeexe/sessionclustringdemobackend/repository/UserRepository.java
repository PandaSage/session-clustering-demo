package com.aeexe.sessionclustringdemobackend.repository;

import com.aeexe.sessionclustringdemobackend.domain.USER;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<USER, String> {
}
