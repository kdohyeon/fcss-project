package fast.campus.netplix.user;

import fast.campus.netplix.user.response.DetailUserResponse;
import fast.campus.netplix.user.response.SimpleUserResponse;
import fast.campus.netplix.user.response.SocialUserResponse;

public interface FetchUserUseCase {
    SimpleUserResponse findSimpleUserByEmail(String email);

    DetailUserResponse findDetailUserByEmail(String email);

    SimpleUserResponse findSimpleUserByProviderId(String providerId);

    SocialUserResponse findKakaoUser(String accessToken);
}
