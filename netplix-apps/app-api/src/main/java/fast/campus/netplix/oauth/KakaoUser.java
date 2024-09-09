package fast.campus.netplix.oauth;

import java.util.Map;

public class KakaoUser implements OAuth2ProviderUser {

    private final Map<String, Object> attributes;

    public KakaoUser(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        Map properties = (Map) attributes.get("properties");
        return properties.get("nickname").toString();
    }
}
