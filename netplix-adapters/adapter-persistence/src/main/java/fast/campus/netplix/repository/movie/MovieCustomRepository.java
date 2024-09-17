package fast.campus.netplix.repository.movie;

import fast.campus.netplix.entity.movie.MovieEntity;

import java.util.Optional;

public interface MovieCustomRepository {
    Optional<MovieEntity> findByMovieName(String name);
}
