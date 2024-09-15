package fast.campus.netplix.authentication;

import fast.campus.netplix.auth.FetchTokenUseCase;
import fast.campus.netplix.auth.UpdateTokenUseCase;
import fast.campus.netplix.auth.response.TokenResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CookieUtil {
    private final String COOKIE_NAME = "token";

    private final UpdateTokenUseCase updateTokenUseCase;

    // 리프레쉬 토큰이 담긴 쿠키를 생성하는 메서드
    public ResponseCookie createCookie(String userId, Duration duration) {
        TokenResponse tokenResponse = updateTokenUseCase.upsertToken(userId);

        return ResponseCookie.from(COOKIE_NAME, tokenResponse.accessToken())
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .maxAge(duration.toMillis())
                .build();
    }

    // 쿠키를 찾아 반환하는 메서드
    public Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    return cookie;
                }
            }
        }

        return null; // 해당하는 쿠키를 찾지 못한 경우 null 반환
    }

    // 쿠키를 삭제하는 메서드
    public Cookie deleteCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setMaxAge(0); // 만료시간을 0으로 설정하여 삭제
        cookie.setPath("/"); // 삭제되는 쿠키의 경로 설정
        return cookie;
    }
}