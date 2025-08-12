package vti.dtn.auth_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vti.dtn.auth_service.filter.JwtFilter;
import vti.dtn.auth_service.oauth2.handler.OAuth2AuthenticationFailureHandler;
import vti.dtn.auth_service.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import vti.dtn.auth_service.oauth2.repository.HttpCookieOAuthorizationRequestRepository;
import vti.dtn.auth_service.oauth2.service.CustomOAuth2UserService;

@Configuration

@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {
            "/oauth2/redirect",
            "/oauth2/authorize",
            "/oauth2/authorize/github",
            "/oauth2/callback/github",
            "/oauth2/redirect",
            "/api/v1/auth/user/register",
            "/api/v1/auth/login",
            "/api/v1/auth/refresh-token",
            "/api/v1/auth/verify"
    };

    private final JwtFilter jwtFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final HttpCookieOAuthorizationRequestRepository httpCookieOAuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint()
                        .baseUri("/oauth2/authorize")
                        .authorizationRequestRepository(httpCookieOAuthorizationRequestRepository)
                        .and()
                        .redirectionEndpoint()
                        .baseUri("/oauth2/callback/*")
                        .and()
                        .userInfoEndpoint()
                        .userService(customOAuth2UserService)
                        .and()
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                        .failureHandler(oAuth2AuthenticationFailureHandler))
                .securityMatcher(WHITE_LIST_URL);
        return http.build();
    }

}
