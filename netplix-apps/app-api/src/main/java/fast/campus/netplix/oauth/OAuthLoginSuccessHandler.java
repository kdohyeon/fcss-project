package fast.campus.netplix.oauth;

import fast.campus.netplix.auth.UpdateTokenUseCase;
import fast.campus.netplix.auth.response.TokenResponse;
import fast.campus.netplix.authentication.CookieUtil;
import fast.campus.netplix.user.FetchUserUseCase;
import fast.campus.netplix.user.RegisterUserUseCase;
import fast.campus.netplix.user.command.SocialUserRegistrationCommand;
import fast.campus.netplix.user.response.SimpleUserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class OAuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final CookieUtil cookieUtil;
    private final FetchUserUseCase fetchUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final UpdateTokenUseCase updateTokenUseCase;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        OAuth2ProviderUser user = getOAuth2ProviderUser(token);

        String providerId = user.getProviderId();
        String provider = user.getProvider();
        String name = user.getName();

        SimpleUserResponse simpleUserByProviderId = fetchUserUseCase.findSimpleUserByProviderId(providerId);
        if (ObjectUtils.isEmpty(simpleUserByProviderId)) {
            registerUserUseCase.registerSocialUser(new SocialUserRegistrationCommand(
                    name,
                    provider,
                    providerId
            ));
        }

        ResponseCookie cookie = cookieUtil.createCookie(user.getProviderId(), Duration.ofHours(3));
        response.addHeader("Set-Cookie", cookie.toString());

        TokenResponse tokenResponse = updateTokenUseCase.upsertToken(user.getProviderId());

        getRedirectStrategy().sendRedirect(request, response, "http://localhost:3000/dashboard");
    }

    private OAuth2ProviderUser getOAuth2ProviderUser(OAuth2AuthenticationToken token) {
        String provider = token.getAuthorizedClientRegistrationId();
        if (provider.equals("kakao")) {
            return new KakaoUser(token.getPrincipal().getAttributes());
        }

        return new DefaultUser();
    }
}
