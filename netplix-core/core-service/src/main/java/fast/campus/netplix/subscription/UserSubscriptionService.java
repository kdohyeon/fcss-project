package fast.campus.netplix.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSubscriptionService implements SubscriptionUseCase, OffUserSubscriptionUseCase {

    private final FetchUserSubscriptionPort fetchUserSubscriptionPort;
    private final UpdateUserSubscriptionPort updateUserSubscriptionPort;

    @Override
    public void offUserSubscription(String userId) {

    }

    @Override
    public void unsubscribe(String userId) {
        UserSubscription userSubscription = fetchUserSubscriptionPort.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("구독중인 서비스가 없습니다."));
        userSubscription.off();
        updateUserSubscriptionPort.update(userSubscription);
    }

    @Override
    public void renewSubscription(String userId) {
        UserSubscription userSubscription = fetchUserSubscriptionPort.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("구독중인 서비스가 없습니다."));

        if (!userSubscription.ableToRenew()) {
            throw new RuntimeException("현재 구독을 갱신할 수 없습니다.");
        }

        userSubscription.renew();
        updateUserSubscriptionPort.update(userSubscription);
    }

    @Override
    public void changeSubscription(String userId, String type) {
        SubscriptionType subscriptionType = SubscriptionType.valueOf(type);
        UserSubscription userSubscription = fetchUserSubscriptionPort.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("구독중인 서비스가 없습니다."));

        if (!userSubscription.ableToChange()) {
            throw new RuntimeException("현재 구독을 갱신할 수 없습니다.");
        }

        userSubscription.change(subscriptionType);
        updateUserSubscriptionPort.update(userSubscription);
    }
}
