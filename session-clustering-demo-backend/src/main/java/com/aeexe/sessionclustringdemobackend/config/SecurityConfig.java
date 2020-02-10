//package com.aeexe.sessionclustringdemobackend.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.session.FindByIndexNameSessionRepository;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private FindByIndexNameSessionRepository sessionRepository;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
////                .antMatchers("/test/**").permitAll()
//                .antMatchers("/test/**").hasRole("BACKEND")
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .and()
//                .httpBasic();
//    }
//}
