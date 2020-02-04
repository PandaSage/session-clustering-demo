package com.aeexe.sessionclustringdemobackend.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TestRestController {
    @Value("${CF_INSTANCE_IP:127.0.0.1}")
    private Object ip;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity welcome() {
        return new ResponseEntity("Welcome. ADMIN SERVER", HttpStatus.OK);
    }

    @RequestMapping(value = "/time", method = RequestMethod.POST)
    public ResponseEntity getTime() {
        return new ResponseEntity(LocalDateTime.now(), HttpStatus.OK);
    }

    @GetMapping("/test/redis")
    public Map redisTest(HttpSession session) {
        UUID uid = Optional.ofNullable((UUID) session.getAttribute("uid"))
                .orElse(UUID.randomUUID());
        session.setAttribute("uid", uid);

        Map m = new HashMap<>();
        m.put("instance_ip", this.ip);
        m.put("uuid", uid.toString());
        return m;
    }
}
