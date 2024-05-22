package io.elasticore.demo.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/**", "/public/**").permitAll()  // 여기에 로그인 없이 접근 허용할 경로를 추가
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()  // 로그인 폼 비활성화
                .httpBasic().disable();  // HTTP 기본 인증 비활성화
    }
}
