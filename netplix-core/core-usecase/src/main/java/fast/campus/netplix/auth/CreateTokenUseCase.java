package fast.campus.netplix.auth;

import fast.campus.netplix.auth.response.TokenResponse;

public interface CreateTokenUseCase {
    TokenResponse createNewToken(String userId);
}
