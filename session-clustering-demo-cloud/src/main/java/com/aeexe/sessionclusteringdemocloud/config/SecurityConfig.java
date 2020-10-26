package com.aeexe.sessionclusteringdemocloud.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .httpBasic()
            .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/*").hasRole("ADMIN")
//                .anyRequest().hasRole("ADMIN");
                .antMatchers("/msg/*").hasRole("ADMIN");
//                .antMatchers("/test**").hasRole("ADMIN")
//                .antMatchers("/backend*").hasRole("ADMIN");
//                .antMatchers("/backend/*").hasRole("BACKEND"
//                .antMatchers().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }
}
