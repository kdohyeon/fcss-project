package fast.campus.netplix.repository.token;

import fast.campus.netplix.entity.token.TokenEntity;

import java.util.Optional;

public interface TokenCustomRepository {
    Optional<TokenEntity> findByUserId(String userId);
}
