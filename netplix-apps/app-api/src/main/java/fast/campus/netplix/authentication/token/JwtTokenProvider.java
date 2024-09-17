package fast.campus.netplix.authentication.token;

import fast.campus.netplix.auth.FetchTokenUseCase;
import fast.campus.netplix.user.response.SimpleUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final FetchTokenUseCase fetchTokenUseCase;

    public Authentication getAuthentication(String accessToken) {
        SimpleUserResponse userResponse = fetchTokenUseCase.findUserByAccessToken(accessToken);
        UserDetails principal = new User(userResponse.username(), "", List.of());
        return new UsernamePasswordAuthenticationToken(principal, "", List.of());
    }
}
