package fast.campus.netplix.subscription;

import java.util.Optional;

public interface InsertUserSubscriptionPort {
    UserSubscription create(String userId);

    Optional<UserSubscription> findByUserId(String userId);
}
