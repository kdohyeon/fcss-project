package fast.campus.netplix.repository.subscription;

import fast.campus.netplix.subscription.UserSubscription;

import java.util.Optional;

public interface UserSubscriptionCustomRepository {
    Optional<UserSubscription> findByUserId(String userId);
}
