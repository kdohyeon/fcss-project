package fast.campus.netplix.token;

import fast.campus.netplix.auth.NetplixToken;

public interface InsertTokenPort {
    NetplixToken create(String userId, String accessToken, String refreshToken);
}
