package fast.campus.netplix.auth;

import fast.campus.netplix.auth.response.TokenResponse;

public interface AuthUseCase {
    TokenResponse login(String email, String password);
}
