package fast.campus.netplix.security;

import fast.campus.netplix.user.FetchUserUseCase;
import fast.campus.netplix.user.response.DetailUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NetplixUserDetailsService implements UserDetailsService {

    private final FetchUserUseCase fetchUserUseCase;

    @Override
    public NetplixAuthUser loadUserByUsername(String email) throws UsernameNotFoundException {
        DetailUserResponse user = fetchUserUseCase.findDetailUserByEmail(email);
        return new NetplixAuthUser(
                user.userId(), user.username(), user.password(),
                user.email(), user.phone(), List.of()
        );
    }
}
