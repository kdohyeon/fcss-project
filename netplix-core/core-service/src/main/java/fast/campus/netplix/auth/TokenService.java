package fast.campus.netplix.auth;

import fast.campus.netplix.auth.response.TokenResponse;
import fast.campus.netplix.token.InsertTokenPort;
import fast.campus.netplix.token.SearchTokenPort;
import fast.campus.netplix.token.UpdateTokenPort;
import fast.campus.netplix.user.FetchUserUseCase;
import fast.campus.netplix.user.response.DetailUserResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService implements FetchTokenUseCase, CreateTokenUseCase, UpdateTokenUseCase {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expire.access-token}")
    private int accessTokenExpireHour;

    @Value("${jwt.expire.refresh-token}")
    private int refreshTokenExpireHour;

    private final SearchTokenPort searchTokenPort;
    private final InsertTokenPort insertTokenPort;
    private final UpdateTokenPort updateTokenPort;
    private final FetchUserUseCase fetchUserUseCase;
    private final KakaoTokenPort kakaoTokenPort;

    @Override
    public TokenResponse findTokenByUserId(String userId) {
        Optional<NetplixToken> tokenOptional = searchTokenPort.findByUserId(userId);
        if (tokenOptional.isEmpty()) {
            throw new RuntimeException();
        }

        NetplixToken netplixToken = tokenOptional.get();
        return TokenResponse.builder()
                .accessToken(netplixToken.getAccessToken())
                .refreshToken(netplixToken.getRefreshToken())
                .build();
    }

    @Override
    public DetailUserResponse findUserByAccessToken(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        String subject = claims.getSubject();
        return fetchUserUseCase.findDetailUserByEmail(subject);
    }

    @Override
    public Boolean validateToken(String accessToken) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    @Override
    public TokenResponse getTokenFromKakao(String code) {
        NetplixToken accessTokenByCode = kakaoTokenPort.getAccessTokenByCode(code);
        return new TokenResponse(accessTokenByCode.getAccessToken(), accessTokenByCode.getRefreshToken());
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    @Override
    public TokenResponse createNewToken(String userId) {
        String accessToken = getToken(userId, Duration.ofHours(accessTokenExpireHour));
        String refreshToken = getToken(userId, Duration.ofHours(refreshTokenExpireHour));
        NetplixToken netplixToken = insertTokenPort.create(userId, accessToken, refreshToken);
        return TokenResponse.builder()
                .accessToken(netplixToken.getAccessToken())
                .refreshToken(netplixToken.getRefreshToken())
                .build();
    }

    @Override
    public TokenResponse updateNewToken(String userId) {
        String accessToken = getToken(userId, Duration.ofHours(accessTokenExpireHour));
        String refreshToken = getToken(userId, Duration.ofHours(refreshTokenExpireHour));
        updateTokenPort.updateToken(userId, accessToken, refreshToken);
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public TokenResponse upsertToken(String userId) {
        return searchTokenPort.findByUserId(userId)
                .map(token -> updateNewToken(userId))
                .orElseGet(() -> createNewToken(userId));
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String getToken(String userId, Duration expireAt) {
        Date now = new Date();
        Instant instant = now.toInstant();

        return Jwts.builder()
                .claim("userId", userId)
                .issuedAt(now)
                .expiration(Date.from(instant.plus(expireAt)))
                .signWith(getSigningKey())
                .compact();
    }
}
