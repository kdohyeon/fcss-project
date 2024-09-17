package fast.campus.netplix.movie;

import fast.campus.netplix.movie.response.MoviePageableResponse;

public interface FetchMovieUseCase {
    MoviePageableResponse fetchFromClient(int page);
    MoviePageableResponse fetchFromDb(int page);
}
