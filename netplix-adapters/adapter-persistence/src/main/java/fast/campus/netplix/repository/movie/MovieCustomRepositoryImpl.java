package fast.campus.netplix.repository.movie;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fast.campus.netplix.entity.movie.MovieEntity;
import fast.campus.netplix.entity.movie.QMovieEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<MovieEntity> findByMovieName(String name) {
        return jpaQueryFactory.selectFrom(QMovieEntity.movieEntity)
                .where(QMovieEntity.movieEntity.movieName.eq(name))
                .fetch()
                .stream()
                .findFirst();
    }
}
