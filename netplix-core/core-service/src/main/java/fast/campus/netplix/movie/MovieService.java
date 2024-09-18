package fast.campus.netplix.movie;

import fast.campus.netplix.movie.download.UserMovieDownloadRoleValidator;
import fast.campus.netplix.movie.response.MoviePageableResponse;
import fast.campus.netplix.movie.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements FetchMovieUseCase, InsertMovieUseCase, DownloadMovieUseCase {

    private final List<UserMovieDownloadRoleValidator> userMovieDownloadRoleValidators;
    private final DownloadMoviePort downloadMoviePort;
    private final TmdbMoviePort tmdbMoviePort;
    private final PersistenceMoviePort persistenceMoviePort;

    @Override
    public MoviePageableResponse fetchFromClient(int page) {
        NetplixPageableMovies movies = tmdbMoviePort.fetchPageable(page);
        return new MoviePageableResponse(movies.getNetplixMovies(), page, movies.isHasNext());
    }

    @Override
    public MoviePageableResponse fetchFromDb(int page) {
        List<NetplixMovie> netplixMovies = persistenceMoviePort.fetchBy(page, 10);
        return new MoviePageableResponse(netplixMovies, page, false);
    }

    @Override
    public void insert(List<NetplixMovie> movies) {
        movies.forEach(persistenceMoviePort::insert);
    }

    @Override
    public MovieResponse download(String userId, String role, String name) {
        long downloadCnt = downloadMoviePort.downloadCntToday(userId);
        boolean validate = userMovieDownloadRoleValidators.stream()
                .filter(validator -> validator.isTarget(role))
                .findFirst()
                .orElseThrow()
                .validate(downloadCnt);

        if (!validate) {
            throw new RuntimeException("더 이상 다운로드 할 수 없습니다.");
        }

        NetplixMovie netplixMovie = persistenceMoviePort.findBy(name);

        downloadMoviePort.save(UserMovieDownload.newDownload(userId, name));

        return new MovieResponse(netplixMovie.getMovieName(), downloadCnt);
    }
}
