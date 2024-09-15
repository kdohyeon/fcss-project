package fast.campus.netplix.auth;

import fast.campus.netplix.auth.response.TokenResponse;
import fast.campus.netplix.user.response.DetailUserResponse;

public interface FetchTokenUseCase {
    TokenResponse findTokenByUserId(String userId);

    DetailUserResponse findUserByAccessToken(String accessToken);

    Boolean validateToken(String accessToken);

    String getTokenFromKakao(String code);
}
