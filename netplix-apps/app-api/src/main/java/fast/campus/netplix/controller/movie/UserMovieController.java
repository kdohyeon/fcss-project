package fast.campus.netplix.controller.movie;

import fast.campus.netplix.authentication.token.JwtTokenProvider;
import fast.campus.netplix.controller.NetplixApiResponse;
import fast.campus.netplix.subscription.LikeMovieUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class UserMovieController {

    private final JwtTokenProvider jwtTokenProvider;
    private final LikeMovieUseCase likeMovieUseCase;

    @PostMapping("/{movieId}/like")
    @PreAuthorize("hasAnyRole('ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
    public NetplixApiResponse<Boolean> likeMovie(@PathVariable String movieId) {
        return NetplixApiResponse.ok(likeMovieUseCase.like(jwtTokenProvider.getUserId(), movieId));
    }

    @PostMapping("/{movieId}/unlike")
    @PreAuthorize("hasAnyRole('ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
    public NetplixApiResponse<Boolean> unlikeMovie(@PathVariable String movieId) {
        return NetplixApiResponse.ok(likeMovieUseCase.unlike(jwtTokenProvider.getUserId(), movieId));
    }
}
