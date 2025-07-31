package vti.dtn.auth_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import vti.dtn.auth_service.dto.request.LoginRequest;
import vti.dtn.auth_service.dto.response.LoginResponse;
import vti.dtn.auth_service.entity.UserEntity;
import vti.dtn.auth_service.repo.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Optional<UserEntity> userEntityByUsername = userRepository.findByUsername(username);
        if (userEntityByUsername.isEmpty()) {
            return LoginResponse.builder()
                    .status(404)
                    .message("User not found")
                    .build();
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserEntity userEntity = userEntityByUsername.get();
        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);

        userEntity.setAccessToken(accessToken);
        userEntity.setRefreshToken(refreshToken);
        userRepository.save(userEntity);

        return LoginResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Login successful")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userEntity.getId())
                .build();
    }

}
