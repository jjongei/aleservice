package net.siriussoft.aleservice.security.config;

import net.siriussoft.aleservice.security.service.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "net.siriussoft.aleservice")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public final static String LOGINID = "loginId";
    public final static String LOGINPASSWORD = "loginPassword";
    public final static String LOGIN_FAIL_URL = "/login?fail=true";

    @Autowired
    @Qualifier("authenticationProvider")
    private AuthenticationProvider authenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/script/**", "/images/**", "/fonts/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/crawling/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login/process")
                    .usernameParameter(LOGINID).passwordParameter(LOGINPASSWORD)
                    .failureUrl("/login")
                .and()
                .logout()
                    .logoutUrl("/j_spring_security_logout")
                    .logoutSuccessUrl("/crawling")
                .and()
                .csrf().disable();
    }
}
