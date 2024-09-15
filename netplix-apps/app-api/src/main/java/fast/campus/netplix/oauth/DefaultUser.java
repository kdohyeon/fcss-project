package fast.campus.netplix.oauth;

import java.util.UUID;

public class DefaultUser implements OAuth2ProviderUser {
    @Override
    public String getProvider() {
        return "default";
    }

    @Override
    public String getProviderId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getName() {
        return "system";
    }
}
