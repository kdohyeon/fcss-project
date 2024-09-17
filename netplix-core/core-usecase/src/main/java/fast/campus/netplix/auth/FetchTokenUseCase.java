package fast.campus.netplix.auth;

import fast.campus.netplix.auth.response.TokenResponse;
import fast.campus.netplix.user.response.UserResponse;

public interface FetchTokenUseCase {
    TokenResponse findTokenByUserId(String userId);

    UserResponse findUserByAccessToken(String accessToken);

    Boolean validateToken(String accessToken);

    String getTokenFromKakao(String code);
}
