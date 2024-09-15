package fast.campus.netplix.kakao;

import fast.campus.netplix.auth.KakaoTokenPort;
import fast.campus.netplix.client.HttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KakaoTokenHttpClient implements KakaoTokenPort {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    private final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";

    private final HttpClient httpClient;

    @Override
    public String getAccessTokenByCode(String code) {
        RestTemplate restTemplate = new RestTemplate();

        // 요청 파라미터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", kakaoRedirectUri);
        params.add("code", code);  // 카카오로부터 받은 인증 코드
        params.add("client_secret", kakaoClientSecret);  // 선택사항

        // HttpHeaders 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // HttpEntity 생성
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // POST 요청 보내기
        ResponseEntity<Map> response = restTemplate.exchange(KAKAO_TOKEN_URL, HttpMethod.POST, request, Map.class);

        return (String) response.getBody().get("access_token");
    }
}
