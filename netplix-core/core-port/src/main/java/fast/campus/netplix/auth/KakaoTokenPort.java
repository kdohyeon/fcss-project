package fast.campus.netplix.auth;

public interface KakaoTokenPort {
    NetplixToken getAccessTokenByCode(String code);
}
