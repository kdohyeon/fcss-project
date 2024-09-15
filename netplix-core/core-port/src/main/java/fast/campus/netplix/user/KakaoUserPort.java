package fast.campus.netplix.user;

import fast.campus.netplix.auth.NetplixUser;

public interface KakaoUserPort {
    NetplixUser findUserFromKakao(String accessToken);
}
