package fast.campus.netplix.entity.subscription;

import fast.campus.netplix.entity.audit.MutableBaseEntity;
import fast.campus.netplix.subscription.SubscriptionType;
import fast.campus.netplix.subscription.UserSubscription;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "user_subscriptions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSubscriptionEntity extends MutableBaseEntity {
    @Id
    @Column(name = "USER_SUBSCRIPTION_ID")
    private String userSubscriptionId;

    @Column(name = "USER_ID")
    private String userId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "SUBSCRIPTION_NAME")
    private SubscriptionType subscriptionName;

    @Column(name = "START_AT")
    private LocalDateTime subscriptionStartAt;

    @Column(name = "END_AT")
    private LocalDateTime subscriptionEndAt;

    @Column(name = "VALID_YN")
    private Boolean validYn;

    public static UserSubscriptionEntity fromDomain(UserSubscription userSubscription) {
        return new UserSubscriptionEntity(
                userSubscription.getUserSubscriptionId(),
                userSubscription.getUserId(),
                userSubscription.getSubscriptionType(),
                userSubscription.getStartAt(),
                userSubscription.getEndAt(),
                userSubscription.getValidYn()
        );
    }
}
