package vti.dtn.api_gateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vti.dtn.api_gateway.exception.ValidationException;
import vti.dtn.api_gateway.response.VerifyTokenResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GatewayFilter {
    private final RestClient restClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("AuthenticationFilter: Processing request for {}", exchange.getRequest().getURI());
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if(!StringUtils.hasText(authHeader)) {
            log.error("Authorization header is missing in request");
            throw new ValidationException(HttpStatus.UNAUTHORIZED, "Authorization header is missing in request");
        }

        VerifyTokenResponse response = restClient.get()
                .uri("http://auth-service:8082/api/v1/auth/verify")
                .header("Authorization", authHeader)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null) {
            log.error("Response from auth service is null");
            throw new ValidationException(HttpStatus.UNAUTHORIZED, "Response from auth service invalid");
        }

        Integer status = response.getStatus();
        String message = response.getMessage();
        String xUserToken = response.getXUserToken();

        if (status == null || status != HttpStatus.OK.value() ||
                !StringUtils.hasText(message) || !message.equals("Success") ||
                !StringUtils.hasText(xUserToken)) {
            log.error("Invalid response from auth service: status={}, message={}, xUserToken={}",
                    status, message, xUserToken);
            throw new ValidationException(HttpStatus.UNAUTHORIZED, "Invalid token or user information");
        }

        log.info("Authentication successful: status={}, message={}, xUserToken={}",
                status, message, xUserToken);

        populateRequestWithHeader(exchange, xUserToken);
        return chain.filter(exchange);
    }

    private void populateRequestWithHeader(ServerWebExchange exchange, String xUserToken) {
        exchange.getRequest().mutate()
                .header("X-User-Token", xUserToken)
                .build();
    }

}
