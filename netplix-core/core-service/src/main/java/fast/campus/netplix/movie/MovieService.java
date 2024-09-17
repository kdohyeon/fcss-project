package fast.campus.netplix.movie;

import fast.campus.netplix.movie.response.MoviePageableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements FetchMovieUseCase, InsertMovieUseCase {

    private final TmdbMoviePort tmdbMoviePort;
    private final PersistenceMoviePort persistenceMoviePort;

    @Override
    public MoviePageableResponse fetchFromClient(int page) {
        NetplixPageableMovies movies = tmdbMoviePort.fetchPageable(page);
        return new MoviePageableResponse(movies.getNetplixMovies(), page, movies.isHasNext());
    }

    @Override
    public MoviePageableResponse fetchFromDb(int page) {
        List<NetplixMovie> netplixMovies = persistenceMoviePort.fetchBy(page);
        return new MoviePageableResponse(netplixMovies, page, false);
    }

    @Override
    public void insert(List<NetplixMovie> movies) {
        movies.forEach(persistenceMoviePort::insert);
    }
}
