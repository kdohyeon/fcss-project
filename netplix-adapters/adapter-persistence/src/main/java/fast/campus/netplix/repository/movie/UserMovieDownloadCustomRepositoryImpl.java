package fast.campus.netplix.repository.movie;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fast.campus.netplix.util.LocalDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static fast.campus.netplix.entity.movie.QUserMovieDownloadEntity.userMovieDownloadEntity;

@Repository
@RequiredArgsConstructor
public class UserMovieDownloadCustomRepositoryImpl implements UserMovieDownloadCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long countDownloadToday(String userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTimeUtil.atStartOfDay(now);
        LocalDateTime end = LocalDateTimeUtil.atEndOfDay(now);

        return jpaQueryFactory.selectFrom(userMovieDownloadEntity)
                .where(userMovieDownloadEntity.userId.eq(userId)
                        .and(userMovieDownloadEntity.createdAt.goe(start))
                        .and(userMovieDownloadEntity.createdAt.lt(end)))
                .fetch()
                .size();
    }
}
