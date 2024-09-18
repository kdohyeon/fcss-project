package fast.campus.netplix.repository.movie;

import fast.campus.netplix.entity.movie.UserMovieDownloadEntity;
import fast.campus.netplix.movie.DownloadMoviePort;
import fast.campus.netplix.movie.UserMovieDownload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserMovieDownloadRepository implements DownloadMoviePort {

    private final UserMovieDownloadJpaRepository userMovieDownloadJpaRepository;

    @Override
    @Transactional
    public UserMovieDownload save(UserMovieDownload domain) {
        return userMovieDownloadJpaRepository.save(UserMovieDownloadEntity.toEntity(domain))
                .toDomain();
    }

    @Override
    @Transactional
    public long downloadCntToday(String userId) {
        return userMovieDownloadJpaRepository.countDownloadToday(userId);
    }
}
