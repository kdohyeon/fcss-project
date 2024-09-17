package fast.campus.netplix.sample;

import fast.campus.netplix.movie.NetplixPageableMovies;
import fast.campus.netplix.tmdb.TmdbMovieListHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SampleClient implements SamplePort {

    private final TmdbMovieListHttpClient tmdbMovieListHttpClient;

    @Override
    public NetplixPageableMovies sample() {
        return tmdbMovieListHttpClient.fetchPageable(2);
    }
}
