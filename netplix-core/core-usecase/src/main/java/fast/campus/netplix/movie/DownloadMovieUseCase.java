package fast.campus.netplix.movie;

import fast.campus.netplix.movie.response.MovieResponse;

public interface DownloadMovieUseCase {
    MovieResponse download(String userId, String role, String name);
}
