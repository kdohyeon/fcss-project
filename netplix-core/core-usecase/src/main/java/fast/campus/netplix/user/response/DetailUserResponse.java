package fast.campus.netplix.user.response;

import lombok.Builder;

@Builder
public record DetailUserResponse(
        String userId,
        String username,
        String password,
        String email,
        String phone
) {
}
