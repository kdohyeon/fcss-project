package fast.campus.netplix.movie;

import fast.campus.netplix.movie.response.MoviePageableResponse;

public interface FetchMovieUseCase {
    MoviePageableResponse fetch(int page);
}
