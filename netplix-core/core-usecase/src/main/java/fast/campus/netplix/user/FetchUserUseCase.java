package fast.campus.netplix.user;

import fast.campus.netplix.user.response.SimpleUserResponse;

public interface FetchUserUseCase {
    SimpleUserResponse findUserByEmail(String email);
}
