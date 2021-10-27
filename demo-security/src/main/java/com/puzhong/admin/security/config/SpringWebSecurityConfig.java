package com.puzhong.admin.security.config;

import com.puzhong.admin.security.denied.CustomAccessDeniedHandler;
import com.puzhong.admin.security.denied.CustomAuthenticationEntryPoint;
import com.puzhong.admin.security.service.LoginUserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Resource
    private CustomAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private LoginUserDetailServiceImpl userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers(authService.getAllPermitUrl()).permitAll()
                .anyRequest().access("@authService.hasPermission(request,authentication)");

        http.exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http.formLogin();
        http.csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService)
        ;
    }

}
