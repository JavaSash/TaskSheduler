package ru.stepenko.taskSheduler.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.stepenko.taskSheduler.handler.CustomAuthenticationFailureHandler;
import ru.stepenko.taskSheduler.handler.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;
    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login*").permitAll() // ресты, которые вызываем без авторизации
                .antMatchers(HttpMethod.POST, "/users").permitAll() // регистрация
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login") // рест для авторизации
                .usernameParameter("login").passwordParameter("password") // параметры post запроса для авторизации
                .failureHandler(failureHandler)
                .successHandler(successHandler)
                .and()
                .csrf().disable()
                .sessionManagement();
    }

    /**
     * @return енкодер для хэширования пароля
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authProvider(passwordEncoder()));
    }
}
