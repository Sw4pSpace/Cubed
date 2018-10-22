package net.cubedserver.webservices.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").failureUrl("/login?error")
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.authorizeRequests()
                .antMatchers("/elements.html", "/webjars/**", "/bower_components/**", "/css/**", "/img/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/403", "/404", "/500").permitAll()
                .anyRequest().authenticated();
    }
}
