package fast.campus.netplix.repository.user;

import fast.campus.netplix.auth.NetplixUser;
import fast.campus.netplix.entity.user.SocialUserEntity;
import fast.campus.netplix.entity.user.UserEntity;
import fast.campus.netplix.exception.UserException;
import fast.campus.netplix.repository.subscription.UserSubscriptionRepository;
import fast.campus.netplix.subscription.UserSubscription;
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
    private final UserSubscriptionRepository userSubscriptionRepository;

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
        Optional<SocialUserEntity> userEntityOptional = socialUserJpaRepository.findByProviderId(providerId);
        if (userEntityOptional.isEmpty()) {
            return Optional.empty();
        }

        SocialUserEntity socialUserEntity = userEntityOptional.get();

        Optional<UserSubscription> byUserId = userSubscriptionRepository.findByUserId(socialUserEntity.getSocialUserId());

        return Optional.of(new NetplixUser(
                socialUserEntity.getSocialUserId(),
                socialUserEntity.getUsername(),
                null,
                null,
                null,
                socialUserEntity.getProvider(),
                socialUserEntity.getProviderId(),
                byUserId.orElse(UserSubscription.newSubscription(socialUserEntity.getSocialUserId()))
                        .getSubscriptionType()
                        .toRole()
        ));
    }

    @Override
    @Transactional
    public NetplixUser create(CreateUser create) {
        UserEntity user = UserEntity.toEntity(create);
        userSubscriptionRepository.create(user.getUserId());
        return userJpaRepository.save(user)
                .toDomain();
    }

    @Override
    @Transactional
    public NetplixUser createSocialUser(String username, String provider, String providerId) {
        SocialUserEntity socialUserEntity = new SocialUserEntity(username, provider, providerId);
        userSubscriptionRepository.create(socialUserEntity.getSocialUserId());
        return socialUserJpaRepository.save(socialUserEntity)
                .toDomain();
    }
}
