package fast.campus.netplix.movie;

public interface DownloadMoviePort {
    UserMovieDownload save(UserMovieDownload domain);

    long downloadCntToday(String userId);
}
