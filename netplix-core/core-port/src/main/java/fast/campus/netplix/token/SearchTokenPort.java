package fast.campus.netplix.token;

import fast.campus.netplix.auth.NetplixToken;

import java.util.Optional;

public interface SearchTokenPort {
    Optional<NetplixToken> findByUserId(String userId);
}
