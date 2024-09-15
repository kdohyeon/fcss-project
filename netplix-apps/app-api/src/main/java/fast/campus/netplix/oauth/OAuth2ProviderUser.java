package fast.campus.netplix.oauth;

public interface OAuth2ProviderUser {
    String getProvider();
    String getProviderId();
    String getName();
}
