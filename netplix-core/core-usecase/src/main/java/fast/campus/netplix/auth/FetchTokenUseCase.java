package fast.campus.netplix.auth;

import fast.campus.netplix.auth.response.TokenResponse;
import fast.campus.netplix.user.response.SimpleUserResponse;

public interface FetchTokenUseCase {
    TokenResponse findTokenByUserId(String userId);

    SimpleUserResponse findUserByAccessToken(String accessToken);

    Boolean validateToken(String accessToken);

    String getTokenFromKakao(String code);
}
