package fast.campus.netplix.user;

import fast.campus.netplix.user.response.DetailUserResponse;
import fast.campus.netplix.user.response.SimpleUserResponse;
import fast.campus.netplix.user.response.SocialUserResponse;
import fast.campus.netplix.user.response.UserResponse;

public interface FetchUserUseCase {
    SimpleUserResponse findSimpleUserByEmail(String email);

    DetailUserResponse findDetailUserByEmail(String email);

    UserResponse findByProviderId(String providerId);

    SocialUserResponse findKakaoUser(String accessToken);
}
