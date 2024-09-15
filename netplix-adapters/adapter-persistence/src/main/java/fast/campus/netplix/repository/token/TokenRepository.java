package fast.campus.netplix.repository.token;

import fast.campus.netplix.auth.NetplixToken;
import fast.campus.netplix.entity.token.TokenEntity;
import fast.campus.netplix.token.InsertTokenPort;
import fast.campus.netplix.token.SearchTokenPort;
import fast.campus.netplix.token.UpdateTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenRepository implements SearchTokenPort, InsertTokenPort, UpdateTokenPort {

    private final TokenJpaRepository tokenJpaRepository;

    @Override
    @Transactional
    public NetplixToken create(String userId, String accessToken, String refreshToken) {
        TokenEntity entity = TokenEntity.toEntity(userId, accessToken, refreshToken);
        return tokenJpaRepository.save(entity)
                .toDomain();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NetplixToken> findByUserId(String userId) {
        return tokenJpaRepository.findByUserId(userId)
                .map(TokenEntity::toDomain);
    }

    @Override
    @Transactional
    public void updateToken(String userId, String accessToken, String refreshToken) {
        Optional<TokenEntity> byUserId = tokenJpaRepository.findByUserId(userId);
        if (byUserId.isEmpty()) {
            throw new RuntimeException();
        }

        TokenEntity tokenEntity = byUserId.get();
        tokenEntity.updateToken(accessToken, refreshToken);
        tokenJpaRepository.save(tokenEntity);
    }
}
