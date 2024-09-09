package fast.campus.netplix.oauth;

public class DefaultUser implements OAuth2ProviderUser {
    @Override
    public String getProvider() {
        return "default";
    }

    @Override
    public String getName() {
        return "system";
    }
}
