package fast.campus.netplix.auth;

import fast.campus.netplix.auth.response.TokenResponse;

public interface UpdateTokenUseCase {
    TokenResponse updateNewToken(String userId);

    TokenResponse upsertToken(String userId);

    TokenResponse reissueToken(String accessToken, String refreshToken);
}
