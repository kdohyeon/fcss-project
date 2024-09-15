package fast.campus.netplix.auth;

public interface KakaoTokenPort {
    String getAccessTokenByCode(String code);
}
