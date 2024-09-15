package fast.campus.netplix.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NetplixToken {
    private final String accessToken;
    private final String refreshToken;

    public NetplixToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
