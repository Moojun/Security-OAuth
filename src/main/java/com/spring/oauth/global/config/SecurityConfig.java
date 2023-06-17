package com.spring.oauth.global.config;

import com.spring.oauth.domain.user.handler.OAuth2AuthenticationSuccessHandler;
import com.spring.oauth.domain.user.service.CustomOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@RequiredArgsConstructor
@EnableWebSecurity              // Spring Security Filter 가 Spring Filter Chain 에 등록이 된다.
public class SecurityConfig {   // WebSecurityConfigurerAdapter is deprecated

    private final CustomOauth2UserService customOauth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1. CSRF 해제
        http.csrf().disable();

        // 2. jSessionId 사용 거부
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 3. UsernamePasswordAuthenticationFilter 비활성화
        http.formLogin().disable();

        // 4. 로그인 인증창이 뜨지 않게 비활성화
        http.httpBasic().disable();

        // URL 별 권한 관리를 설정하는 옵션의 시작점입니다. authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있습니다.
        http.authorizeRequests()
                .antMatchers("/**").permitAll();

        http.oauth2Login().userInfoEndpoint()
                .userService(customOauth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler);
//                .failureHandler();

        return http.build();
    }

}
