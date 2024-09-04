package fast.campus.netplix.sample;

import fast.campus.netplix.tmdb.TmdbMovieListHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SampleClient implements SamplePort {

    private final TmdbMovieListHttpClient tmdbMovieListHttpClient;
    @Override
    public String sample() {
        return tmdbMovieListHttpClient.getMovieListNowPlaying(2);
    }
}
