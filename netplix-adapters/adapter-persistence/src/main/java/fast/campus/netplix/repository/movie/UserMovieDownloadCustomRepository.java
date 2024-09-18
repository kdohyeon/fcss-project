package fast.campus.netplix.repository.movie;

public interface UserMovieDownloadCustomRepository {
    long countDownloadToday(String userId);
}
