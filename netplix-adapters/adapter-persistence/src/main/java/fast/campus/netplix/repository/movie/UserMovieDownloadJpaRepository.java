package fast.campus.netplix.repository.movie;

import fast.campus.netplix.entity.movie.UserMovieDownloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMovieDownloadJpaRepository extends JpaRepository<UserMovieDownloadEntity, String>, UserMovieDownloadCustomRepository {

}
