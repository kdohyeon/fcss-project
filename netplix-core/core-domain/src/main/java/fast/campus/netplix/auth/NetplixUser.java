package fast.campus.netplix.auth;

import lombok.Getter;

import java.util.List;

@Getter
public class NetplixUser {
    private final String userId;
    private final String encryptedPassword;
    private final List<NetplixRole> roles;

    public NetplixUser(String userId, String encryptedPassword, List<NetplixRole> roles) {
        this.userId = userId;
        this.encryptedPassword = encryptedPassword;
        this.roles = roles;
    }
}
