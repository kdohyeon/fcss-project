package fast.campus.netplix.user.response;

import fast.campus.netplix.auth.NetplixUser;
import org.apache.commons.lang3.StringUtils;

public record UserResponse(
        String userId,
        String username,
        String password,
        String email,
        String phone,
        String provider,
        String providerId,
        String role
) {
    public static UserResponse toUserResponse(NetplixUser netplixUser) {
        return new UserResponse(
                netplixUser.getUserId(),
                netplixUser.getUsername(),
                StringUtils.defaultIfBlank(netplixUser.getEncryptedPassword(), "password"),
                StringUtils.defaultIfBlank(netplixUser.getEmail(), "email@email.com"),
                StringUtils.defaultIfBlank(netplixUser.getPhone(), "010-0000-0000"),
                StringUtils.defaultIfBlank(netplixUser.getProvider(), "no-provider"),
                StringUtils.defaultIfBlank(netplixUser.getProviderId(), "no-provider-id"),
                netplixUser.getRole()
        );
    }
}
