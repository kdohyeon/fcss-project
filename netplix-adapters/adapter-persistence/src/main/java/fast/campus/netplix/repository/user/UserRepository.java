package fast.campus.netplix.repository.user;

import fast.campus.netplix.auth.NetplixUser;
import fast.campus.netplix.entity.user.SocialUserEntity;
import fast.campus.netplix.entity.user.UserEntity;
import fast.campus.netplix.exception.UserException;
import fast.campus.netplix.user.CreateUser;
import fast.campus.netplix.user.InsertUserPort;
import fast.campus.netplix.user.SearchUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements SearchUserPort, InsertUserPort {

    private final UserJpaRepository userJpaRepository;
    private final SocialUserJpaRepository socialUserJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<NetplixUser> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(UserEntity::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public NetplixUser getByEmail(String email) {
        Optional<NetplixUser> byEmail = findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new UserException.UserDoesNotExistException();
        }

        return byEmail.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NetplixUser> findByProviderId(String providerId) {
        return socialUserJpaRepository.findByProviderId(providerId)
                .map(SocialUserEntity::toDomain);
    }

    @Override
    @Transactional
    public NetplixUser create(CreateUser create) {
        UserEntity user = UserEntity.toEntity(create);
        return userJpaRepository.save(user)
                .toDomain();
    }

    @Override
    @Transactional
    public NetplixUser createSocialUser(String username, String provider, String providerId) {
        SocialUserEntity socialUserEntity = new SocialUserEntity(username, provider, providerId);
        return socialUserJpaRepository.save(socialUserEntity)
                .toDomain();
    }
}
