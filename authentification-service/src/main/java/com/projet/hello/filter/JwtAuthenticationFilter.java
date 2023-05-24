package com.projet.hello.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
        private AuthenticationManager authenticationManager;

        public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
                this.authenticationManager = authenticationManager;
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
                System.out.println("attemptAuthentication");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                System.out.println(username + "    " + password);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                username, password);
                return authenticationManager.authenticate(authenticationToken);
        }

        // generation des tockens pour une connexion autorizer ( connexion accepter)
        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        FilterChain chain,
                        Authentication authResult) throws IOException, ServletException {

                System.out.println("successfulAuthentication  ici genaration de tocken");

                User user = (User) authResult.getPrincipal();

                Algorithm algorithm = Algorithm.HMAC256("mySecret1234");
                String jwtaccessToken = JWT.create() // creation de tocken access
                                .withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + 180000)) // 1*60*1000 =60000 ,,
                                                                                              // 3*60*1000=180000
                                .withIssuer(request.getRequestURI().toString())
                                .withClaim("roles", user.getAuthorities().stream().map(ga -> ga.getAuthority())
                                                .collect(Collectors.toList()))
                                .sign(algorithm);
                response.setHeader("Authorization", jwtaccessToken);

                String jwtrefrenshToken = JWT.create() // creation de tocken refresh
                                .withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + 900000)) // 15 * 60 * 1000= 900000
                                .withIssuer(request.getRequestURI().toString())
                                .sign(algorithm);

                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtaccessToken);
                idToken.put("refresh-token", jwtrefrenshToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
        }

}
