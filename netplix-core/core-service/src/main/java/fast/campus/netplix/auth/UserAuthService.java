package fast.campus.netplix.auth;

import fast.campus.netplix.auth.command.UserRegistrationCommand;
import fast.campus.netplix.auth.response.UserRegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements RegisterUserUseCase {
    @Override
    public UserRegistrationResponse register(UserRegistrationCommand request) {
        return null;
    }
}
