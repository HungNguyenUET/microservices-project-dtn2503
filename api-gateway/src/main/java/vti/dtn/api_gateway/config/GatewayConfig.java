package vti.dtn.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/api/v1/auth/**")
                        .uri("lb://auth-service"))
                .route("account-service", r -> r.path("/api/v1/accounts/**")
                        .uri("lb://account-service"))
                .route("department-service", r -> r.path("/api/v1/departments/**")
                        .uri("lb://department-service"))
                .build();
    }
}
