package fast.campus.netplix.repository.subscription;

import fast.campus.netplix.entity.subscription.UserSubscriptionEntity;
import fast.campus.netplix.subscription.FetchUserSubscriptionPort;
import fast.campus.netplix.subscription.InsertUserSubscriptionPort;
import fast.campus.netplix.subscription.UpdateUserSubscriptionPort;
import fast.campus.netplix.subscription.UserSubscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserSubscriptionRepository implements UpdateUserSubscriptionPort, InsertUserSubscriptionPort, FetchUserSubscriptionPort {

    private final UserSubscriptionJpaRepository userSubscriptionJpaRepository;

    @Override
    @Transactional
    public UserSubscription create(String userId) {
        UserSubscription userSubscription = UserSubscription.newSubscription(userId);
        UserSubscriptionEntity entity = UserSubscriptionEntity.toEntity(userSubscription);
        return userSubscriptionJpaRepository.save(entity)
                .toDomain();
    }

    @Override
    @Transactional
    public Optional<UserSubscription> findByUserId(String userId) {
        return userSubscriptionJpaRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public UserSubscription update(UserSubscription userSubscription) {
        return userSubscriptionJpaRepository.save(UserSubscriptionEntity.toEntity(userSubscription))
                .toDomain();
    }
}
