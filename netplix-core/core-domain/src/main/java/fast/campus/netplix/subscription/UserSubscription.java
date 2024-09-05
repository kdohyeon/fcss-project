package fast.campus.netplix.subscription;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Getter
public class UserSubscription {
    private String userSubscriptionId;
    private String userId;
    private SubscriptionType subscriptionType;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Boolean validYn;

    public UserSubscription(String userSubscriptionId, String userId, SubscriptionType subscriptionType, LocalDateTime startAt, LocalDateTime endAt, Boolean validYn) {
        this.userSubscriptionId = UUID.randomUUID().toString();;
        this.userId = userId;
        this.subscriptionType = subscriptionType;
        this.startAt = startAt;
        this.endAt = getEndAt(startAt);
        this.validYn = true;
    }

    public void off() {
        this.validYn = false;
    }

    public void on(SubscriptionType subscription) {
        if (this.validYn) {
//            log.info("현재 구독권을 소지하고 있으므로 신규 구독을 할 수 없습니다. userId={}", this.userId);
            return;
        }

        this.subscriptionType = subscription;
        this.startAt = LocalDateTime.now();
        this.endAt = getEndAt(this.endAt);
        this.validYn = true;
    }

    private LocalDateTime getEndAt(LocalDateTime startAt) {
        return startAt.plus(Duration.ofDays(30));
    }
}
