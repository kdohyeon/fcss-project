package fast.campus.netplix.repository.movie;

import fast.campus.netplix.entity.movie.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, String>, MovieCustomRepository {
    Optional<MovieEntity> findByMovieName(String movieName);
}
