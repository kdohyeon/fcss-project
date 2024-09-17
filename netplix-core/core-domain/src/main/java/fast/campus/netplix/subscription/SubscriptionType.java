package fast.campus.netplix.subscription;

import lombok.Getter;

@Getter
public enum SubscriptionType {
    FREE("무료 구독권"),
    BRONZE("브론즈 구독권"),
    SILVER("실버 구독권"),
    GOLD("골드 구독권"),
    ;

    private final String desc;

    SubscriptionType(String desc) {
        this.desc = desc;
    }

    public String toRole() {
        return "ROLE_" + this.name();
    }
}
