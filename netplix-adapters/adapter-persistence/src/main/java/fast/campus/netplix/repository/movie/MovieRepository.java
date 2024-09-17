package fast.campus.netplix.repository.movie;

import fast.campus.netplix.entity.movie.MovieEntity;
import fast.campus.netplix.movie.NetplixMovie;
import fast.campus.netplix.movie.PersistenceMoviePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MovieRepository implements PersistenceMoviePort {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    @Transactional
    public List<NetplixMovie> fetchBy(int page) {
        return null;
    }

    @Override
    @Transactional
    public String insert(NetplixMovie netplixMovie) {
        MovieEntity entity = MovieEntity.toEntity(netplixMovie);
        Optional<MovieEntity> byMovieName = movieJpaRepository.findByMovieName(netplixMovie.getMovieName());

        if (byMovieName.isEmpty()) {
            log.info("신규 영화 추가={}", netplixMovie.getMovieName());
            movieJpaRepository.save(entity);
            return entity.getMovieId();
        }

        return null;
    }
}
