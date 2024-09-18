package fast.campus.netplix.controller.movie;

import fast.campus.netplix.authentication.token.JwtTokenProvider;
import fast.campus.netplix.controller.NetplixApiResponse;
import fast.campus.netplix.movie.DownloadMovieUseCase;
import fast.campus.netplix.movie.FetchMovieUseCase;
import fast.campus.netplix.movie.response.MoviePageableResponse;
import fast.campus.netplix.movie.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {

    private final JwtTokenProvider jwtTokenProvider;
    private final FetchMovieUseCase fetchMovieUseCase;
    private final DownloadMovieUseCase downloadMovieUseCase;

    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('ROLE_FREE', 'ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
    public NetplixApiResponse<MoviePageableResponse> search(@RequestParam int page) {
        MoviePageableResponse fetch = fetchMovieUseCase.fetchFromDb(page);
        return NetplixApiResponse.ok(fetch);
    }

    @PostMapping("/{movieName}/download")
    @PreAuthorize("hasAnyRole('ROLE_FREE', 'ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
    public NetplixApiResponse<MovieResponse> download(@PathVariable String movieName) {
        String userId = jwtTokenProvider.getUserId();
        String role = jwtTokenProvider.getRole();
        return NetplixApiResponse.ok(downloadMovieUseCase.download(userId, role, movieName));
    }
}
