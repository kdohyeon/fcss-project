package fast.campus.netplix.auth;

import fast.campus.netplix.auth.AuthUseCase;
import fast.campus.netplix.auth.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    @Override
    public TokenResponse login(String email, String password) {

        return null;
    }
}
