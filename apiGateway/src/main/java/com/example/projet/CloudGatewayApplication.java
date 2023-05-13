package com.example.projet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudGatewayApplication {

        // // @Bean
        // DiscoveryClientRouteDefinitionLocator dynamiqueRoutes(ReactiveDiscoveryClient
        // rdc,
        // DiscoveryLocatorProperties dlp) {
        // return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
        // }

        @Bean
        public RouteLocator Routes(RouteLocatorBuilder builder) {
                return builder.routes()
                                .route("authentification", r -> r.path("/users/**")
                                                .filters(f -> f.stripPrefix(1))
                                                .uri("lb://CCONNEXION-SERVICE"))
                                .route("servicecandidat", r -> r.path("/candidats/**")
                                                .filters(f -> f.stripPrefix(1))
                                                .uri("lb://CANDIDAT-SERVICE"))
                                .route("serviceoffre", r -> r.path("/microservice2/**")
                                                .filters(f -> f.stripPrefix(1))
                                                .uri("http://localhost:9092"))
                                .build();
        }

        public static void main(String[] args) {
                SpringApplication.run(CloudGatewayApplication.class, args);
        }

}
