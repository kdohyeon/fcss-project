package fast.campus.netplix.controller.auth;

import fast.campus.netplix.auth.RegisterUserUseCase;
import fast.campus.netplix.auth.command.UserRegistrationCommand;
import fast.campus.netplix.controller.NetplixApiResponse;
import fast.campus.netplix.controller.auth.request.UserRegistrationRequest;
import fast.campus.netplix.auth.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/api/v1/register")
    public NetplixApiResponse<UserRegistrationResponse> register(
            @RequestBody UserRegistrationRequest request
    ) {
        UserRegistrationCommand command = UserRegistrationCommand.builder()
                .username(request.getUsername())
                .encryptedPassword(request.getPassword())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        return NetplixApiResponse.ok(registerUserUseCase.register(command));
    }
}
