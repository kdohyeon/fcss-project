package fast.campus.netplix.auth.response;

import lombok.Builder;

@Builder
public record TokenResponse(String grantType, String accessToken, String refreshToken) {
}
