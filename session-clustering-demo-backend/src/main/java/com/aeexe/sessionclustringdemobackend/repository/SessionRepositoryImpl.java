package com.aeexe.sessionclustringdemobackend.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class SessionRepositoryImpl implements FindByIndexNameSessionRepository {


    @Override
    public Map findByIndexNameAndIndexValue(String s, String s1) {
        return null;
    }

    @Override
    public Session createSession() {
        return null;
    }

    @Override
    public void save(Session session) {

    }

    @Override
    public Session findById(String s) {
        return null;
    }

    @Override
    public void deleteById(String s) {

    }
}
