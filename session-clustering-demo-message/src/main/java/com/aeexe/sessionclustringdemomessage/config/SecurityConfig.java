package com.aeexe.sessionclustringdemomessage.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").hasRole("ADMIN")
//                .antMatchers("/test/**").hasRole("ADMIN")
//                .anyRequest().hasRole("ADMIN")
//                .antMatchers().hasRole("MESSAGE");
                .antMatchers("/time").hasRole("ADMIN")
                .antMatchers("/test/*").hasRole("ADMIN");

    }



}
