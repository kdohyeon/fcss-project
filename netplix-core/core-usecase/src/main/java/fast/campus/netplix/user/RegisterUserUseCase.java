package fast.campus.netplix.user;

import fast.campus.netplix.user.command.UserRegistrationCommand;
import fast.campus.netplix.user.response.UserRegistrationResponse;

public interface RegisterUserUseCase {
    UserRegistrationResponse register(UserRegistrationCommand request);
}
