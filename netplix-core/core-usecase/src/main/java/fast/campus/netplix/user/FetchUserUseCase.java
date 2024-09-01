package fast.campus.netplix.user;

import fast.campus.netplix.user.response.DetailUserResponse;
import fast.campus.netplix.user.response.SimpleUserResponse;

public interface FetchUserUseCase {
    SimpleUserResponse findSimpleUserByEmail(String email);
    DetailUserResponse findDetailUserByEmail(String email);
}
