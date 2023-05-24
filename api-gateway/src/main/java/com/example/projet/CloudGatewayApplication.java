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

        // acceder et reconnaitre les services par gateway a partir de service de
        // dicoverte Eureka d'une maniere dynamique en utilisant
        // cette methode de dynamiqueRoutes pour recuperer la liste des candidats de
        // service candidat a partir de service Eureka
        @Bean
        DiscoveryClientRouteDefinitionLocator dynamiqueRoutes(ReactiveDiscoveryClient rdc,
                        DiscoveryLocatorProperties dlp) {
                return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
        }

        // @Bean
        public RouteLocator Routes(RouteLocatorBuilder builder) {
                return builder.routes()
                                .route("serviceCandidat", r -> r.path("/candidats/**")
                                                .uri("lb://CANDIDAT-SERVICE"))
                                .route("ServiceAgence", r -> r.path("/agence/**")
                                                .filters(f -> f.stripPrefix(1))
                                                .uri("lb://AGENCE-SERVICE"))
                                .route("serAuthentification", r -> r.path("/athentification/**")
                                                .uri("lb://AUTHENTIFICATION-SERVICE"))
                              
                                .build();
        }

        public static void main(String[] args) {
                SpringApplication.run(CloudGatewayApplication.class, args);
        }

}
