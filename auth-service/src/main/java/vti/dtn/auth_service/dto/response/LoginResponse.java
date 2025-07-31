package vti.dtn.auth_service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private int status;
    private String message;

    private Long userId;
    private String accessToken;
    private String refreshToken;
}
