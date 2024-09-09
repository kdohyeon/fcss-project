package fast.campus.netplix.oauth;

import lombok.Getter;

@Getter
public enum Provider {
    KAKAO_PROVIDER("kakao"),
    ;

    private final String providerName;

    Provider(String providerName) {
        this.providerName = providerName;
    }
}
