package fast.campus.netplix.controller.auth;

import fast.campus.netplix.auth.FetchTokenUseCase;
import fast.campus.netplix.auth.UpdateTokenUseCase;
import fast.campus.netplix.auth.response.TokenResponse;
import fast.campus.netplix.controller.NetplixApiResponse;
import fast.campus.netplix.controller.auth.request.LoginRequest;
import fast.campus.netplix.security.NetplixAuthUser;
import fast.campus.netplix.user.FetchUserUseCase;
import fast.campus.netplix.user.response.SimpleUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UpdateTokenUseCase updateTokenUseCase;
    private final FetchTokenUseCase fetchTokenUseCase;
    private final FetchUserUseCase fetchUserUseCase;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public NetplixApiResponse<TokenResponse> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        NetplixAuthUser principal = (NetplixAuthUser) authentication.getPrincipal();

        TokenResponse tokenResponse = updateTokenUseCase.upsertToken(principal.getEmail());

        return NetplixApiResponse.ok(tokenResponse);
    }

    @PostMapping("/callback")
    public NetplixApiResponse<TokenResponse> kakaoCallback(@RequestBody Map<String, String> request) {
        String code = request.get("code");

        String tokenFromKakao = fetchTokenUseCase.getTokenFromKakao(code);
        String providerId = fetchUserUseCase.findKakaoProviderId(tokenFromKakao);
        return NetplixApiResponse.ok(fetchTokenUseCase.findTokenByUserId(providerId));
    }
}
