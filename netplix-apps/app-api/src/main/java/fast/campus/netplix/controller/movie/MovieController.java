package fast.campus.netplix.controller.movie;

import fast.campus.netplix.controller.NetplixApiResponse;
import fast.campus.netplix.movie.FetchMovieUseCase;
import fast.campus.netplix.movie.response.MoviePageableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {

    private final FetchMovieUseCase fetchMovieUseCase;

    @PostMapping("/search")
    public NetplixApiResponse<MoviePageableResponse> search(@RequestParam int page) {
        MoviePageableResponse fetch = fetchMovieUseCase.fetchFromDb(page);
        return NetplixApiResponse.ok(fetch);
    }
}
