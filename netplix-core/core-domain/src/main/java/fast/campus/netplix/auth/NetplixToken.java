package fast.campus.netplix.auth;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NetplixToken {
    private final String accessToken;
    private final String refreshToken;
    private final LocalDateTime accessTokenExpireAt;
    private final LocalDateTime refreshTokenExpireAt;

    public NetplixToken(String accessToken, String refreshToken, LocalDateTime accessTokenExpireAt, LocalDateTime refreshTokenExpireAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpireAt = accessTokenExpireAt;
        this.refreshTokenExpireAt = refreshTokenExpireAt;
    }
}
