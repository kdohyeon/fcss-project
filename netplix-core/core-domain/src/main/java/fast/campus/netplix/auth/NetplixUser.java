package fast.campus.netplix.auth;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NetplixUser {
    private final String userId;
    private final String username;
    private final String encryptedPassword;
    private final String email;
    private final String phone;
    private final String provider;
    private final String providerId;
    private final List<NetplixRole> roles;

    public NetplixUser(String userId, String username, String encryptedPassword, String email, String phone, String provider, String providerId, List<NetplixRole> roles) {
        this.userId = userId;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.email = email;
        this.phone = phone;
        this.provider = provider;
        this.providerId = providerId;
        this.roles = roles;
    }
}
