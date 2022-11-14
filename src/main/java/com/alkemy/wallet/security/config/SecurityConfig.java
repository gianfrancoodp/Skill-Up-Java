package com.alkemy.wallet.security.config;


import com.alkemy.wallet.model.Role;
import com.alkemy.wallet.security.filter.JwtRequestFilter;
import com.alkemy.wallet.security.service.Impl.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    private UserAuthService userAuthService;
    private JwtRequestFilter jwtRequestFilter;

    private Role role = new Role();

    @Autowired
    public void SecurityConfig(@Lazy UserAuthService userAuthService,
                               @Lazy JwtRequestFilter jwtRequestFilter){
        this.userAuthService = userAuthService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userAuthService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    protected SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/auth/*").permitAll()
                .antMatchers(HttpMethod.POST,"/role").permitAll()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/swagger-ui/index.html#/*",
                        "/webjars/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/users/:id").hasAuthority(role.getName().ADMIN.name())
                .antMatchers(HttpMethod.GET,"/users").hasAuthority(role.getName().ADMIN.name())
                .antMatchers(HttpMethod.GET,"/account/:userId").hasAuthority(role.getName().ADMIN.name())
                .antMatchers(HttpMethod.POST,"/account").hasAuthority(role.getName().USER.name())
                .antMatchers(HttpMethod.POST,"/transactions/sendArs").hasAuthority(role.getName().USER.name())
                .antMatchers(HttpMethod.POST,"/transactions/sendUsd").hasAuthority(role.getName().USER.name())
                .antMatchers(HttpMethod.GET,"/account/balance").hasAuthority(role.getName().USER.name())
                .antMatchers(HttpMethod.POST,"/transactions/deposit").hasAuthority(role.getName().USER.name())
                .antMatchers(HttpMethod.POST,"/transactions/payment").hasAuthority(role.getName().USER.name())
                .antMatchers(HttpMethod.POST,"/fixedDeposit").hasAuthority(role.getName().USER.name())
                .antMatchers(HttpMethod.GET,"/transactions/:userId").hasAnyAuthority(
                        role.getName().ADMIN.name(),role.getName().USER.name()
                )
                .antMatchers(HttpMethod.GET,"/transactions/:id/").hasAuthority(role.getName().USER.name())
                .antMatchers(HttpMethod.PATCH,"/transactions/:id/").hasAuthority(role.getName().USER.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


}
