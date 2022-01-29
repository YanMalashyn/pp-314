package com.example.springcrudsecurityboot.security;


import com.example.springcrudsecurityboot.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // сервис, с помощью которого тащим пользователя
    private final UserDetailsService userDetailsService;
    // класс, в котором описана логика перенаправления пользователей по ролям
    private final LoginSuccessHandler loginSuccessHandler;



    public SecurityConfig( UserDetailsService userDetailsService,
                          LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    // конфигурация для прохождения аутентификации
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выключаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();


        // делаем страницу регистрации недоступной для авторизированных пользователей
        http.authorizeRequests()
                // доступность всем
                .antMatchers("/").permitAll()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
                // разрешаем входить на /user пользователям с ролью User и админу
                .antMatchers("/user").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                // защищенные URL только для админа
                .antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                // Spring сам подставит свою логин форму
                .and().formLogin()
                // подключаем наш SuccessHandler для перенеправления по ролям
                .successHandler(loginSuccessHandler);
    }

    //Шифровальщик
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
