package com.aeexe.sessionclustringdemomessage.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestRestController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity welcome() {
        return new ResponseEntity("Welcome. ADMIN SERVER", HttpStatus.OK);
    }

    @RequestMapping(value = "/time", method = RequestMethod.POST)
    public ResponseEntity getTime() {
        return new ResponseEntity(LocalDateTime.now(), HttpStatus.OK);
    }
}
