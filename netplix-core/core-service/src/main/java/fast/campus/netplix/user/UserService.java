package fast.campus.netplix.user;

import fast.campus.netplix.auth.CreateUser;
import fast.campus.netplix.auth.InsertUserPort;
import fast.campus.netplix.auth.NetplixUser;
import fast.campus.netplix.auth.SearchUserPort;
import fast.campus.netplix.exception.UserException;
import fast.campus.netplix.user.command.UserRegistrationCommand;
import fast.campus.netplix.user.response.DetailUserResponse;
import fast.campus.netplix.user.response.SimpleUserResponse;
import fast.campus.netplix.user.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase, FetchUserUseCase {

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

    @Override
    public SimpleUserResponse findSimpleUserByEmail(String email) {
        Optional<NetplixUser> byEmail = searchUserPort.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new UserException.UserDoesNotExistException();
        }
        NetplixUser netplixUser = byEmail.get();

        return new SimpleUserResponse(netplixUser.getUsername(), netplixUser.getEmail(), netplixUser.getPhone());
    }

    @Override
    public DetailUserResponse findDetailUserByEmail(String email) {
        Optional<NetplixUser> byEmail = searchUserPort.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new UserException.UserDoesNotExistException();
        }
        NetplixUser netplixUser = byEmail.get();

        return DetailUserResponse
                .builder()
                .userId(netplixUser.getUserId())
                .username(netplixUser.getUsername())
                .email(netplixUser.getEmail())
                .password(netplixUser.getEncryptedPassword())
                .phone(netplixUser.getPhone())
                .build();
    }
}
