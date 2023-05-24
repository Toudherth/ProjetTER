package com.inter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.inter.filter.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Abonnements
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/abonnements").hasAnyAuthority("agence", "admin",
                "employeur");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/abonnements/{id}").hasAnyAuthority("agence", "admin",
                "employeur");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/abonnements").hasAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/abonnements").hasAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/abonnements/{id}").hasAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/abonnements/{id}").hasAuthority("admin");

        // Agence
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/agences/inscription").hasAuthority("agence");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/agences").hasAnyAuthority("agence", "admin");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/agences/{id}").hasAnyAuthority("agence", "admin");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/agences").hasAuthority("agence");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/agences/{id}").hasAnyAuthority("agence", "admin");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/agences/{id}").hasAuthority("agence");

        http.authorizeRequests().anyRequest().authenticated();
        // ajouter la classe JWTAuthorizationFilter
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}