package com.projet.hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projet.hello.filter.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Offres
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/offres").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/offres/{nomoffre}").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/offres/postuler").hasAuthority("candidat");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/offres").hasAnyAuthority("agence", "employeur");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/offres/**").hasAnyAuthority("agence", "employeur");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/offres/**").hasAnyAuthority("agence", "employeur");
        // Candidats
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/candidats").hasAnyAuthority("agence", "employeur");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/candidats/{nomcandidat}").hasAnyAuthority("employeur",
                "agence");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/candidats/ajouter").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/candidats/**").hasAuthority("candidat");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/candidats/**").hasAuthority("candidat");
        // ALL
        http.authorizeRequests().antMatchers("/candidats/**").hasAnyAuthority("candidat", "agence", "admin",
                "employeur");
        http.authorizeRequests().antMatchers("/offres/**").hasAnyAuthority("candidat", "agence", "admin", "employeur");
        http.authorizeRequests().anyRequest().authenticated();
        // ajouter la callse JWTAuthorizationFilter
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
