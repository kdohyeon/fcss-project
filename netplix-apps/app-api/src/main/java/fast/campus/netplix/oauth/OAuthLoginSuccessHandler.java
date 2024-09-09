package fast.campus.netplix.oauth;

import fast.campus.netplix.user.FetchUserUseCase;
import fast.campus.netplix.user.response.SimpleUserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final FetchUserUseCase fetchUserUseCase;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        OAuth2ProviderUser user = getOAuth2ProviderUser(token);

        String providerId = user.getProvider();
        String name = user.getName();

        SimpleUserResponse simpleUserByProviderId = fetchUserUseCase.findSimpleUserByProviderId(providerId);

        getRedirectStrategy().sendRedirect(request, response, "http://localhost:8080/");
    }

    private OAuth2ProviderUser getOAuth2ProviderUser(OAuth2AuthenticationToken token) {
        String provider = token.getAuthorizedClientRegistrationId();
        if (provider.equals("kakao")) {
            return new KakaoUser(token.getPrincipal().getAttributes());
        }

        return new DefaultUser();
    }
}
