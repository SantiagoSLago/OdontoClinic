package com.BackIntegrador.clinicaIntegrador.Seguridad;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorizeConfig->{
                    authorizeConfig.requestMatchers("").permitAll();
                    authorizeConfig.requestMatchers("/error").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
                .formLogin()
                .and().build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("usuario")
                        .password("{noop}pass123")
                        .authorities("ROLE_user")
                        .build()
        );
    }

}
