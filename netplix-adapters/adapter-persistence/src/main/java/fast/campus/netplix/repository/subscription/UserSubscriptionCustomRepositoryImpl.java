package fast.campus.netplix.repository.subscription;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fast.campus.netplix.entity.subscription.QUserSubscriptionEntity;
import fast.campus.netplix.entity.subscription.UserSubscriptionEntity;
import fast.campus.netplix.subscription.UserSubscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserSubscriptionCustomRepositoryImpl implements UserSubscriptionCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<UserSubscription> findByUserId(String userId) {
        return jpaQueryFactory.selectFrom(QUserSubscriptionEntity.userSubscriptionEntity)
                .fetch()
                .stream()
                .findFirst()
                .map(UserSubscriptionEntity::toDomain);
    }
}
