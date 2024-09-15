package fast.campus.netplix.controller.auth;

import fast.campus.netplix.auth.FetchTokenUseCase;
import fast.campus.netplix.auth.UpdateTokenUseCase;
import fast.campus.netplix.auth.response.TokenResponse;
import fast.campus.netplix.authentication.CookieUtil;
import fast.campus.netplix.controller.NetplixApiResponse;
import fast.campus.netplix.controller.auth.request.LoginRequest;
import fast.campus.netplix.security.NetplixAuthUser;
import fast.campus.netplix.user.FetchUserUseCase;
import fast.campus.netplix.user.response.SimpleUserResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    private final CookieUtil cookieUtil;

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

    @PostMapping("/logout")
    public NetplixApiResponse<Void> logout(HttpServletResponse response) {
        Cookie cookie = cookieUtil.deleteCookie();
        response.addCookie(cookie);
        return NetplixApiResponse.ok(null);
    }

    @PostMapping("/callback")
    public NetplixApiResponse<TokenResponse> kakaoCallback(@RequestBody Map<String, String> request) {
        String code = request.get("code");

        TokenResponse tokenFromKakao = fetchTokenUseCase.getTokenFromKakao(code);

        SimpleUserResponse kakaoUser = fetchUserUseCase.findKakaoUser(tokenFromKakao.accessToken());
        log.info("kakao user={}", kakaoUser.username());

        return NetplixApiResponse.ok(tokenFromKakao);
    }
}
