package com.aeexe.sessionclustringdemobackend.config;


import com.aeexe.sessionclustringdemobackend.repository.TokenRepositoryImpl;
import com.aeexe.sessionclustringdemobackend.serviceImpl.AuthProviderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.web.cors.CorsUtils;

@RequiredArgsConstructor
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String REMEMBER_ME_KEY = "remember-me";

    private final AuthProviderImpl authProvider;

    private final UserDetailsService userDetailsService;

    private final FindByIndexNameSessionRepository sessionRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //preFlight 요청은 인증처리를 안하겠다.
                // preFlight 요청은 authrization 헤더가 줄 이유가 없으므로 401응답을 하면 안된다.
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/login-processing", "/login", "/error").permitAll()
                .antMatchers("/**").authenticated();

        http.csrf().disable();

        http.formLogin()
                .loginPage("/")
                .loginPage("/login")
                .loginProcessingUrl("/login-processing")
                .failureUrl("/error")
                .defaultSuccessUrl("/main", true)
                .usernameParameter("id")
                .passwordParameter("pw");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", REMEMBER_ME_KEY);

        http.sessionManagement()
                //세션 허용개수 : 1개
                .maximumSessions(1)
                //이미 로그인 중일 경우 로그인이 안된다.
                //false일 경우 기존 사용자의 세션이 종료된다.
                .maxSessionsPreventsLogin(false)
                //중복 로그인이 발생했을 경우 이동할 주소(원인을 알려줄 주소)
                .expiredUrl("/error")
                //만료된 세션 전략?
                //.expiredSessionStrategy()
                //세션 레지스트리?
                .sessionRegistry(sessionRegistry());

        http.rememberMe()
                .tokenValiditySeconds(6048000)
                .key(REMEMBER_ME_KEY)
                //.rememberMeParameter(REMEMBER_ME_KEY)
                .rememberMeServices(persistentTokenBasedRememberMeServices())
                .rememberMeCookieName(REMEMBER_ME_KEY);

        //http.authenticationProvider(authProvider);
    }

    @Bean
    public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices =
                new PersistentTokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService, persistentTokenRepository());
        return persistentTokenBasedRememberMeServices;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        TokenRepositoryImpl tokenRepositoryImpl = new TokenRepositoryImpl();
        return tokenRepositoryImpl;
    }

    @Bean
    SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
    }

    @RequiredArgsConstructor
    @Configuration
    public static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        private final UserDetailsService userDetailsService;

        @Bean
        PasswordEncoder passwordEncoder() {
            // 스프링에서 제공하는 기본 암호 인코더
            // return new BCryptPasswordEncoder();
            // 커스텀 인코더를 사용하고있다.
            return new MyPasswordEncoder();
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
    }

    // 암호 인코더 커스텀 설정
    public static class MyPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence rawPassword) {
            //sha-2 / sha-3 같은 해시를 접목시킬 수 있다.
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            // rawPassword 현재 들어온 값 | encodedPassword 매칭되는 계정에 있는 값
            return encodedPassword.equals(encode(rawPassword));
        }
    }
}