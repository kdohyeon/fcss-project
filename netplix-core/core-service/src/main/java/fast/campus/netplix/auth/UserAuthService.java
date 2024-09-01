package fast.campus.netplix.auth;

import fast.campus.netplix.auth.command.UserRegistrationCommand;
import fast.campus.netplix.auth.response.UserRegistrationResponse;
import fast.campus.netplix.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthService implements RegisterUserUseCase {

    private final SearchUserPort searchUserPort;
    private final InsertUserPort insertUserPort;

    @Override
    public UserRegistrationResponse register(UserRegistrationCommand request) {
        Optional<NetplixUser> byEmail = searchUserPort.findByEmail(request.email());
        if (byEmail.isPresent()) {
            throw new UserException.UserAlreadyExistException();
        }

        NetplixUser netplixUser = insertUserPort.create(
                CreateUser.builder()
                        .username(request.username())
                        .encryptedPassword(request.encryptedPassword())
                        .email(request.email())
                        .phone(request.phone())
                        .build()
        );
        return new UserRegistrationResponse(netplixUser.getUsername(), netplixUser.getEmail(), netplixUser.getPhone());
    }
}
