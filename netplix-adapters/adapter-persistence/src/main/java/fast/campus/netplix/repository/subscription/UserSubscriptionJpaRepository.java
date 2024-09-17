package fast.campus.netplix.repository.subscription;

import fast.campus.netplix.entity.subscription.UserSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubscriptionJpaRepository extends JpaRepository<UserSubscriptionEntity, String>, UserSubscriptionCustomRepository {
}
