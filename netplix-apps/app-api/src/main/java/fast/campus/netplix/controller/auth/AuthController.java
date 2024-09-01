package fast.campus.netplix.controller.auth;

import fast.campus.netplix.auth.AuthUseCase;
import fast.campus.netplix.auth.response.TokenResponse;
import fast.campus.netplix.controller.NetplixApiResponse;
import fast.campus.netplix.controller.auth.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/login")
    public NetplixApiResponse<TokenResponse> login(
            @RequestBody LoginRequest request
    ) {
        return NetplixApiResponse.ok(authUseCase.login(request.getEmail(), request.getPassword()));
    }
}
