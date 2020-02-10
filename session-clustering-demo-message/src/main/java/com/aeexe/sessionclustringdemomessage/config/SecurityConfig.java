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
                .httpBasic().disable()
                .csrf().disable()
                // TODO: 2020-02-10 참조 https://docs.spring.io/spring-security/site/docs/4.2.13.RELEASE/apidocs/org/springframework/security/config/http/SessionCreationPolicy.html
                // TODO: 2020-02-10 SessionCreationPolicy.NEVER 가 맞다.. redis session 을 사용하기 때문에 토큰 방식이더라도.. jwt 등을 활용 할땐  SessionCreationPolicy.STATELESS
                //        org.springframework.security.config.http
                //        Enum SessionCreationPolicy
                //        java.lang.Object
                //        java.lang.Enum<SessionCreationPolicy>
                //        org.springframework.security.config.http.SessionCreationPolicy
                //        All Implemented Interfaces:
                //        Serializable, Comparable<SessionCreationPolicy>
                //
                //        public enum SessionCreationPolicy
                //                extends Enum<SessionCreationPolicy>
                //  Specifies the various session creation policies for Spring Security.
                //        Since:
                //        3.1
                //        Enum Constant Summary
                //        Enum Constants
                //        Enum Constant and Description
                //        ALWAYS
                //        Always create an HttpSession
                //        IF_REQUIRED
                //        Spring Security will only create an HttpSession if required
                //                NEVER
                //        Spring Security will never create an HttpSession, but will use the HttpSession if it already exists
                //                STATELESS
                //        Spring Security will never create an HttpSession and it will never use it to obtain the SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                //                .antMatchers("/test/**").hasRole("ADMIN")
                .anyRequest().hasRole("ADMIN");
        //                .antMatchers().hasRole("MESSAGE");
//                    .antMatchers("/time").hasRole("ADMIN")
//                    .antMatchers("/test/*").hasRole("ADMIN");

    }



}
