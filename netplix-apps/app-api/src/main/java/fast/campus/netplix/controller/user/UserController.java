package fast.campus.netplix.controller.user;

import fast.campus.netplix.user.FetchUserUseCase;
import fast.campus.netplix.user.RegisterUserUseCase;
import fast.campus.netplix.user.command.UserRegistrationCommand;
import fast.campus.netplix.user.response.SimpleUserResponse;
import fast.campus.netplix.controller.NetplixApiResponse;
import fast.campus.netplix.controller.user.request.UserRegistrationRequest;
import fast.campus.netplix.user.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final FetchUserUseCase fetchUserUseCase;

    @GetMapping("/{email}")
    public NetplixApiResponse<SimpleUserResponse> findUserByEmail(
            @PathVariable String email
    ) {
        return NetplixApiResponse.ok(fetchUserUseCase.findUserByEmail(email));
    }


    @PostMapping("/register")
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
