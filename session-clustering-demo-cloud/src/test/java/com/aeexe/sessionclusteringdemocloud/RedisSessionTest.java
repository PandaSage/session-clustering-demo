package com.aeexe.sessionclusteringdemocloud;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("rawtypes")
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisSessionTest {
    @Autowired
    private FindByIndexNameSessionRepository sessionRepository;

    @Test
    public void testSession() throws Exception {
        System.out.println(sessionRepository.findByPrincipalName("admin"));
        Assert.assertNotNull( "error !!!!!!!!!!!!!!!!!!!",sessionRepository.findByPrincipalName("admin"));
    }
}
