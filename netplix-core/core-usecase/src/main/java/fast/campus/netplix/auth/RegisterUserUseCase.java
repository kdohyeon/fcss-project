package fast.campus.netplix.auth;

import fast.campus.netplix.auth.command.UserRegistrationCommand;
import fast.campus.netplix.auth.response.UserRegistrationResponse;

public interface RegisterUserUseCase {
    UserRegistrationResponse register(UserRegistrationCommand request);
}
