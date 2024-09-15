package fast.campus.netplix.repository.user;

import fast.campus.netplix.entity.user.SocialUserEntity;

import java.util.Optional;

public interface SocialUserCustomRepository {
    Optional<SocialUserEntity> findByProviderId(String providerId);
}
