package fast.campus.netplix.auth.response;

import lombok.Builder;

@Builder
public record TokenResponse(String accessToken, String refreshToken) {
}
