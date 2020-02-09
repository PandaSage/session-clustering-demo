package com.aeexe.sessionclustringdemomessage.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class TestRestController {
    @Value("${CF_INSTANCE_IP:127.0.0.1}")
    private Object ip;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity welcome() {
        return new ResponseEntity("Welcome. ADMIN SERVER", HttpStatus.OK);
    }

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public ResponseEntity getTime() {
        return new ResponseEntity(LocalDateTime.now(), HttpStatus.OK);
    }

    @GetMapping("/test/redis")
    public Map redisTest(HttpServletRequest request, HttpSession session) {
        log.info(String.format("###### has rule : %s", request.isUserInRole("ROLE_ADMIN")));
        log.info(String.format("###### x-aeexe-token at response server : %s", request.getHeader("x-aeexe-token")));
        String token = Optional.ofNullable(request.getHeader("x-aeexe-token")).orElseThrow(NoTokenException::new);
        session.setAttribute("x-aeexe-token", token);

        Map m = new HashMap<>();
        m.put("instance_ip", this.ip);
        m.put("x-aeexe-token", token);
        return m;
    }

    static class NoTokenException extends NullPointerException {
        public NoTokenException() {
            super();
            log.error("has not token!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n please check your request");
        }

        public NoTokenException(String s) {
            super(s);
            log.error("has not token!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! : %s", s);
        }
    }
}
