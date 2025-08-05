package vti.dtn.auth_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class VerifyTokenResponse {
    private Integer status;
    private String message;
    private String xUserToken;
}
