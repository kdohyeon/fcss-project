package fast.campus.netplix.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private static final String KAKAO_USERINFO_API_URL = "https://kapi.kakao.com/v2/user/me";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 액세스 토큰 추출
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        String tokenValue = accessToken.getTokenValue();

        // 카카오 사용자 정보 API 호출
        String kakaoUserInfo = getKakaoUserInfo(tokenValue);

        // kakaoUserInfo에 필요한 로직을 추가하거나, 해당 정보를 저장 가능

        return oAuth2User;
    }

    private String getKakaoUserInfo(String accessToken) {
        // 카카오 사용자 정보 API를 호출하는 로직
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);  // 액세스 토큰을 Authorization 헤더에 추가

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_USERINFO_API_URL,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();  // 사용자 정보를 JSON 문자열로 반환
    }
}
